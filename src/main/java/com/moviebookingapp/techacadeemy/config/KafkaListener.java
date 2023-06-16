//package com.moviebookingapp.techacadeemy.config;
//
//import com.moviebookingapp.techacadeemy.entities.KafkaAdminData;
//import com.moviebookingapp.techacadeemy.entities.Show;
//import com.moviebookingapp.techacadeemy.entities.Ticket;
//import com.moviebookingapp.techacadeemy.repository.AdminDataRepostory;
//import com.moviebookingapp.techacadeemy.repository.ShowRepository;
//import com.moviebookingapp.techacadeemy.repository.TicketRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaListener {
//    Logger LOG = LoggerFactory.getLogger(KafkaListener.class);
//
//    @Autowired
//    ShowRepository showRepository;
//
//    @Autowired
//    AdminDataRepostory adminDataRepostory;
//
//    @org.springframework.kafka.annotation.KafkaListener(
//            topics = "updateTicketStatus", groupId = "myGroup"
//    )
//    void listenerupdateTicketStatus(Object data) {
//        showRepository.save((Show) data);
//        KafkaAdminData kafkaAdminData = new KafkaAdminData("Ticket Status Updated for Movie:: " + ((Show) data).getMovie().getMovieId(), ((Show) data).getMovie());
//        adminDataRepostory.save(kafkaAdminData);
//        System.out.println(data);
//    }
//
//
//    @org.springframework.kafka.annotation.KafkaListener(
//            topics = "totalTicketAvailable", groupId = "myGroup"
//    )
//    void listenertotalTicketAvailable(Object data) {
//        KafkaAdminData kafkaAdminData = new KafkaAdminData("Total Ticket Available for Movie:: " + ((Show) data).getMovie().getMovieId(), ((Show) data).getNoAvailableTicket());
//        adminDataRepostory.save(kafkaAdminData);
//        System.out.println(data);
//    }
//
//
//    @org.springframework.kafka.annotation.KafkaListener(
//            topics = "totalTicketBooked", groupId = "myGroup"
//    )
//    void listenertotalTicketBooked(Ticket data, String movieId) {
//        KafkaAdminData kafkaAdminData = new KafkaAdminData("New Ticket Booked for Movie:: " + movieId, data);
//        adminDataRepostory.save(kafkaAdminData);
//        System.out.println("NEW TICKET BOOKED" + data);
//    }
//}