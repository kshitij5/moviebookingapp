package com.moviebookingapp.techacadeemy.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
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

@Document("booking")
public class Booking {
    @Id
	@Indexed(unique = true)
	private String bookingId;
	@NotNull
	private int noOfTickets;
    @NotNull
	private double totalCost;
    @NotBlank
	private EBookingstatus bookingStatus = EBookingstatus.BOOKED;
	@DocumentReference
	private Movie movie;
	@DocumentReference
	private Theatre theatre;
	private LocalDateTime timeStamp;
    @NotBlank
	private String userId;
    @NotBlank
	private String hallId;
	private List<Ticket> ticket;

	public Booking(int noOfTickets, double totalCost, EBookingstatus bookingStatus, String userId, String hallId) {
		this.noOfTickets = noOfTickets;
		this.totalCost = totalCost;
		this.bookingStatus = bookingStatus;
		this.userId = userId;
		this.hallId = hallId;
	}
}