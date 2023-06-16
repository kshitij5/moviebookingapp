package com.moviebookingapp.techacadeemy.controller;

import com.moviebookingapp.techacadeemy.entities.Payment;
import com.moviebookingapp.techacadeemy.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1.0/moviebooking/payment")
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(TheatreController.class);
    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/add")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {

        logger.info("-------PAYMENT NODE IS BEING CREATED" + payment + "---------");
        return ResponseEntity.ok(paymentRepository.save(payment));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> viewPaymentList() {

        logger.info("-------List Of Shows Fetched Successfully---------");
        return ResponseEntity.ok(paymentRepository.findAll());
    }

    @GetMapping("/search/{paymentId}")
    public ResponseEntity<Payment> viewPaymentList(@PathVariable String paymentId) {

        logger.info("-------List Of Shows Fetched Successfully---------");
        return ResponseEntity.ok(paymentRepository.findById(paymentId).get());
    }
}