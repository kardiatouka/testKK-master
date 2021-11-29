package com.example.test;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    //test register method
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() {
        User user = User.builder()
                .username("farmataka")
                .birthdate(LocalDate.of(1993, 1, 8))
                .country("France")
                .gender("MALE")
                .phoneNumber("122-150-4045")
                .build();

        User registeredUser = userRepository.save(user);
        assertThat(registeredUser.getId()).isGreaterThan(0);
    }

    //test findById method
    @Test
    @Order(2)
    @Rollback(value = false)
    public void getUserByIdTest() {
        User user = userRepository.findById(1L).get();
        assertThat(user.getId()).isEqualTo(1L);
    }
}
