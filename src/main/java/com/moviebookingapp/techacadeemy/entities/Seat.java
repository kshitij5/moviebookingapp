package com.moviebookingapp.techacadeemy.entities;

public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seatId;
	private String seatNumber;
	private String type;
	private double price;

	@Enumerated(EnumType.STRING)
	private SeatStatus status;
	@JsonIgnore
	@ManyToOne
	private Ticket ticket;
}

