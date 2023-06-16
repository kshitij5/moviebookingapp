package com.moviebookingapp.techacadeemy.entities;

import java.util.Date;

import javax.validation.constraints.NotBlank;
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

@Document("movie")
public class Movie {

	@Id
	@Indexed(unique = true)
    private String movieId;
    @NotBlank
    private String movieName;
    private String moviePosterUrl;
    private String movieHours;
    private String movieGenre;
    private String movieLanguage;
    private String movieDescription;
    private Double movieRating;
    private Date movieDate;
//	private Show show;
}