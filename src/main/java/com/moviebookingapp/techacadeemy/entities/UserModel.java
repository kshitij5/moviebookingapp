package com.moviebookingapp.techacadeemy.entities;


import java.util.Set;

import javax.management.relation.Role;
import javax.validation.constraints.Pattern;

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
@Document("user")
public class UserModel {

	@Id
	@Indexed(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String loginId;
	private String firstName;
	private String lastName;
	@Indexed(unique = true)
	private String emailId;
	private String password;
	private String confirmPassword;
	private String conactNumber;
	
	@DBRef
	private Set<Role> roles;
	
}