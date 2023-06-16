package com.moviebookingapp.techacadeemy.controller;

import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.techacadeemy.entities.ERole;
import com.moviebookingapp.techacadeemy.entities.Role;
import com.moviebookingapp.techacadeemy.entities.User;
import com.moviebookingapp.techacadeemy.exception.UserNotFoundException;
import com.moviebookingapp.techacadeemy.payload.request.LoginRequest;
import com.moviebookingapp.techacadeemy.payload.request.PasswordResetRequest;
import com.moviebookingapp.techacadeemy.payload.request.SignupRequest;
import com.moviebookingapp.techacadeemy.payload.response.MessageResponse;
import com.moviebookingapp.techacadeemy.payload.response.UserInfoResponse;
import com.moviebookingapp.techacadeemy.repository.RoleRepository;
import com.moviebookingapp.techacadeemy.repository.UserRepository;
import com.moviebookingapp.techacadeemy.security.jwt.JwtUtils;
import com.moviebookingapp.techacadeemy.security.services.UserDetailsImpl;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1.0/moviebooking/auth")
public class AuthController {

	Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	/**
	 * logs in user
	 * 
	 * @param loginRequest
	 * @return User
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
			throws Exception, UserNotFoundException {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmailId(), loginRequest.getPassword()));

		logger.info("-------User logged in---------");

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new UserInfoResponse(userDetails.getLoginId(), userDetails.getFirstName(),
						userDetails.getLastName(), userDetails.getEmailId(), roles));
	}

	/**
	 * signup user
	 * 
	 * @param signUpRequest
	 * @return user
	 * @throws Exception
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
		if (!(signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())))
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Password doesn't match!"));

		if (userRepository.existsByEmailId(signUpRequest.getEmailId())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmailId(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getContactNumber());

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		logger.info("-------User Signed Up---------");
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	/**
	 * @param emailId
	 * @param contactNumber
	 * @return Boolean
	 * @throws UserNotFoundException
	 */
	@GetMapping("/canResetPassword")
	public ResponseEntity<User> passwordResetabble(@RequestParam String emailId, @RequestParam String contactNumber)
			throws UserNotFoundException {
		if (userRepository.existsByEmailId(emailId) && userRepository.existsByContactNumber(contactNumber)
				&& !userRepository.findById(emailId).get().getRoles().contains("ROLE_ADMIN")) {
			logger.info("-------Password can be Reset---------");
			return new ResponseEntity<>(userRepository.findByEmailId(emailId).get(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * reset user password// dummy implementation
	 * 
	 * @param username/emailId
	 * @return HTTP status
	 * @throws UserNotFoundException
	 */
	@PostMapping("/{userId}/forgot")
	public ResponseEntity<?> forgot(@PathVariable String userId, @RequestBody PasswordResetRequest passwordReset)
			throws UserNotFoundException {

		ResponseEntity<User> response = null;
		if (userRepository.existsById(userId)) {
			User user = userRepository.findById(userId).get();
			user.setPassword(encoder.encode(passwordReset.getConfirmPassword()));
			userRepository.save(user);
			logger.info("-------Password Reset Successfully---------");
			response = new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new UserNotFoundException("User with " + userId + " id dosen't exist");
		}

		return response;
	}

	@GetMapping("/test")
	public ResponseEntity<?> all() {
		return ResponseEntity.ok(new MessageResponse("APIs working fine"));
	}

}