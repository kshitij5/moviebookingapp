package com.moviebookingapp.techacadeemy.repository;

import com.moviebookingapp.techacadeemy.entities.KafkaAdminData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminDataRepostory extends MongoRepository<KafkaAdminData, String> {
}