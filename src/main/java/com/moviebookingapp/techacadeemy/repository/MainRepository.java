package com.moviebookingapp.techacadeemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moviebookingapp.techacadeemy.entities.UserModel;

public interface MainRepository extends MongoRepository<UserModel, String> {
	
}