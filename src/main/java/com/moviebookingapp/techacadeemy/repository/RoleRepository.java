package com.moviebookingapp.techacadeemy.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moviebookingapp.techacadeemy.entities.User;
import com.moviebookingapp.techacadeemy.entities.ERole;
import com.moviebookingapp.techacadeemy.entities.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}
