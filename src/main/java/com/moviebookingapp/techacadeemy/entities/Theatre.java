package com.moviebookingapp.techacadeemy.entities;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "theatre")
public class Theatre {
    @Id
	@Indexed(unique = true)
    private String theatreId;
    @NotBlank
    private String theatreName;
    @NotBlank
    private String theatreCity;
//    @DocumentReference
//	private List<Show> show;
}