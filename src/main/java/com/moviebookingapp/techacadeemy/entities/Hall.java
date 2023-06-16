package com.moviebookingapp.techacadeemy.entities;

import java.util.List;

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
@Document(collection = "hall")
public class Hall {

	@Id
	@Indexed(unique = true)
	private String hallId;
	private Show show;
	private Movie movie;
	@NotNull
	private int rowNumer;
	@NotNull
	private int columnNumer;
	List<Seat> seats;

	public Hall(String hallId, int rowNumer, int columnNumer, List<Seat> seats, Show show) {
		this.hallId = hallId;
		this.rowNumer = rowNumer;
		this.columnNumer = columnNumer;
		this.seats = seats;
		this.show = show;
	}

}