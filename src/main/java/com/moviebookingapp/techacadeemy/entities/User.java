package com.moviebookingapp.techacadeemy.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
public class User {

	@Id
	@Indexed(unique = true)
	private String loginId;
	private String firstName;
	private String lastName;
	@Indexed(unique = true)
	private String emailId;
	private String password;
	private String confirmPassword;
	private String conactNumber;
	
}