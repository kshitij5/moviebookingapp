package com.moviebookingapp.techacadeemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moviebookingapp.techacadeemy.entities.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {
	UserModel findByEmail(String email);
}
