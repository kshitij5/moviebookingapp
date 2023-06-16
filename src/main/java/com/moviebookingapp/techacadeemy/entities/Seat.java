package com.moviebookingapp.techacadeemy.entities;

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
@Document(collection = "seat")
public class Seat {
	@Id
	@Indexed(unique = true)
	private String seatId;
	@NotNull
	private int rowNumer;
	@NotNull
	private int columnNumer;
	@NotNull
	private ESeatStatus status = ESeatStatus.AVAILABLE;
	@NotNull
	private double price;
	public Seat(@NotNull int rowNumer, @NotNull int columnNumer, @NotNull ESeatStatus status, @NotNull double price) {
		super();
		this.rowNumer = rowNumer;
		this.columnNumer = columnNumer;
		this.status = status;
		this.price = price;
	}
}
