package com.moviebookingapp.techacadeemy.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payment")
public class Payment {

    @Id
    @Indexed(unique = true)
    private String paymentID;
    private double amount;
    private LocalDateTime timeStamp;
    private String bookingID;
}