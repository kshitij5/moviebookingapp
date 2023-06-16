package com.moviebookingapp.techacadeemy.repository;

import com.moviebookingapp.techacadeemy.entities.User;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    User user;

    @BeforeEach
    void setUp() {
        user = new User("test1","test2","test@test.com","password","12345689");
        user = userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.delete(user);
//        userRepository.deleteAll();
    }
    @Test
    void findByEmailId() {
        User expectedUser = userRepository.findByEmailId(user.getEmailId()).get();
        assertThat(expectedUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(expectedUser.getLastName()).isEqualTo(user.getLastName());
        assertThat(expectedUser.getContactNumber()).isEqualTo(user.getContactNumber());
    }

    @Test
    void findByContactNumber() {
        User expectedUser = userRepository.findByContactNumber(user.getContactNumber()).get();
        assertThat(expectedUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(expectedUser.getLastName()).isEqualTo(user.getLastName());
        assertThat(expectedUser.getEmailId()).isEqualTo(user.getEmailId());
    }


    @Test
    void existsByEmailId() {
        Boolean expected = userRepository.existsByEmailId(user.getEmailId());
        assertThat(expected).isTrue();
    }

    @Test
    void existsByContactNumber() {
        Boolean expected = userRepository.existsByContactNumber(user.getContactNumber());
        assertThat(expected).isTrue();
    }
}