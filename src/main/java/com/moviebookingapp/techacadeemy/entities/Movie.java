package com.moviebookingapp.techacadeemy.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank
    private String theatreName;
    @NotNull
    private Long numberOfTickets;
    @NotBlank
    private String ticketStatus;
    @NotNull
    private Integer releaseDate;

}
