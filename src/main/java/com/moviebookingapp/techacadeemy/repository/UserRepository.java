package com.moviebookingapp.techacadeemy.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moviebookingapp.techacadeemy.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmailId(String email);
	Optional<User> findByContactNumber(String contactNumber);

	Boolean existsByEmailId(String emailId);
	Boolean existsByContactNumber(String emailId);
}
