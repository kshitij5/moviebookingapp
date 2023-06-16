package com.moviebookingapp.techacadeemy.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
@Document(collection = "show")
public class Show {

	@Id
	@Indexed(unique = true)
	private String showId;
	@NotBlank
	private String showName;
	@NotNull
	private Integer noAvailableTicket;
	@NotBlank
	private ETicketStatus ticketStatus = ETicketStatus.BOOK_ASAP;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;
	@DocumentReference
	private Movie movie;
	@DocumentReference
	private Theatre theatre;
	@DocumentReference
	private Hall hall;

	public Show(@NotBlank String showName, LocalDateTime showStartTime, LocalDateTime showEndTime) {
		super();
		this.showName = showName;
		this.showStartTime = showStartTime;
		this.showEndTime = showEndTime;
	}

	public Show(String showId, String showName, int noAvailableTicket, ETicketStatus ticketStatus, Movie movie, Theatre theatre) {
		this.showName = showName;
		this.noAvailableTicket = noAvailableTicket;
		this.ticketStatus = ticketStatus;
		this.movie = movie;
		this.theatre = theatre;
		this.showId = showId;
	}
}