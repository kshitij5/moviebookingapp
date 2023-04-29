package com.moviebookingapp.techacadeemy.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviebookingapp.techacadeemy.entities.User;
import com.moviebookingapp.techacadeemy.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String emaild) throws UsernameNotFoundException {
    User user = userRepository.findByEmailId(emaild)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with emaild: " + emaild));

    return UserDetailsImpl.build(user);
  }

}