package com.moviebookingapp.techacadeemy.repository;

import com.moviebookingapp.techacadeemy.entities.ERole;
import com.moviebookingapp.techacadeemy.entities.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;
    Role user = null;
    Role admin = null;
    @Test
    void findByName() {
        Role userResult = roleRepository.findByName(ERole.ROLE_USER).get();
        Role adminResult = roleRepository.findByName(ERole.ROLE_ADMIN).get();

        assertThat(userResult).isInstanceOf(Role.class);
        assertThat(adminResult).isInstanceOf(Role.class);
    }

    @AfterEach
    void tearDown() {
//        roleRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        roleRepository.deleteAll();
        user = roleRepository.save(new Role(ERole.ROLE_USER));
        admin = roleRepository.save(new Role(ERole.ROLE_ADMIN));
    }
}