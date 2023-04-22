package com.moviebookingapp.techacadeemy.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("roles")
public class Role {
	@Id
	private String id;
	@Indexed(unique = true)
	private String role;
}
