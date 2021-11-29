package com.example.test;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.service.UserService;
import com.example.test.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void testGetUsers() {
        User user1 = User.builder()
                .username("kardiatouka")
                .birthdate(LocalDate.of(1991, 2, 10))
                .country("France")
                .gender("FEMALE")
                .phoneNumber("422-150-4046")
                .build();
        User user2 = User.builder()
                .username("farmataka")
                .birthdate(LocalDate.of(1993, 1, 8))
                .country("France")
                .gender("MALE")
                .phoneNumber("122-150-4045")
                .build();
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        User user = User.builder()
                .id(2001L)
                .username("farmataka")
                .birthdate(LocalDate.of(1993, 1, 8))
                .country("France")
                .gender("MALE")
                .phoneNumber("122-150-4045")
                .build();
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
        Mockito.when(userRepository.findById(2001L)).thenReturn(java.util.Optional.ofNullable(user));

        User user2 = userService.getUserById(2001L);

        assertEquals("farmataka", user2.getUsername());
        assertEquals("France", user2.getCountry());
        assertEquals("MALE", user2.getGender());
    }

    @Test
    void testRegisterUser() {
        User user = User.builder()
                .username("farmataka")
                .birthdate(LocalDate.of(1993, 1, 8))
                .country("France")
                .gender("MALE")
                .phoneNumber("122-150-4045")
                .build();

        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);

        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }


}
