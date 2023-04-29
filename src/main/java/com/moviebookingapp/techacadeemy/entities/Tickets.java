package com.moviebookingapp.techacadeemy.entities;

import java.util.Set;

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
@Document(collection = "tickets")
public class Tickets {

	@Id
	@Indexed(unique = true)
	private String ticketId;

	@NotBlank
	private int noOfTickets;
	
	@NotBlank
	private Set<String> seatNumber;
	
	@NotBlank
    private String movieName;

	@NotBlank
    private String theatreName;
}
