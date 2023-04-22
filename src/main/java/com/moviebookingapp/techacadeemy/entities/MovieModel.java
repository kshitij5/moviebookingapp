package com.moviebookingapp.techacadeemy.entities;

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
public class MovieModel {

    private String movieName;
    private String theatreName;
    private Long numberOfTickets;
    private Long seatNumbers;

}
