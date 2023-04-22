package com.moviebookingapp.techacadeemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moviebookingapp.techacadeemy.entities.UserModel;
import com.moviebookingapp.techacadeemy.entities.Role;

public interface RoleRepository extends MongoRepository<UserModel, String> {
	Role findByRol(String role);
}
