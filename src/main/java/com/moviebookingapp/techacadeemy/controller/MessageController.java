package com.moviebookingapp.techacadeemy.controller;

import com.moviebookingapp.techacadeemy.entities.Booking;
import com.moviebookingapp.techacadeemy.entities.KafkaAdminData;
import com.moviebookingapp.techacadeemy.repository.AdminDataRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/moviebooking/kafka")
public class MessageController {
    @Autowired
    AdminDataRepostory adminDataRepostory;
    @GetMapping("/msg")
    public ResponseEntity<List<KafkaAdminData>> getMsg() {
        return new ResponseEntity(adminDataRepostory.findAll(), HttpStatus.OK);
    }
}