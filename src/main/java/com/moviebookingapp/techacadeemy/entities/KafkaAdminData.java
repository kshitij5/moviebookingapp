package com.moviebookingapp.techacadeemy.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "adminMessages")
public class KafkaAdminData {

    @Id
    @Indexed(unique = true)
    private String id;
    @NotNull
    private String message;
    private Object updatedObject;

    public KafkaAdminData(String message, Object updatedObject) {
        this.message = message;
        this.updatedObject = updatedObject;
    }
}