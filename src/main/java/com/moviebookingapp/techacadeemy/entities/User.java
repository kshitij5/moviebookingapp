package com.moviebookingapp.techacadeemy.entities;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {

	@Id
	@Indexed(unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String loginId;

	@NotBlank
	@Size(max = 20)
	private String firstName;

	@NotBlank
	@Size(max = 20)
	private String lastName;

	@Indexed(unique = true)
	@NotBlank
	@Size(max = 50)
	@Email
	private String emailId;

	@NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 10)
	private String contactNumber;

	@DBRef
	private Set<Role> roles = new HashSet<>();

	public User(String firstName, String lastName, String emailId, String password, String contactNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.contactNumber = contactNumber;
	}

}