package com.example.test;

import com.example.test.model.User;
import com.example.test.serviceImpl.UserServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    void testGetUsers() throws Exception {
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
        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("kardiatouka")));
    }

    @Test
    void testGetUserById() throws Exception {
        User user = User.builder()
                .id(2001L)
                .username("farmataka")
                .birthdate(LocalDate.of(1993, 1, 8))
                .country("France")
                .gender("MALE")
                .phoneNumber("122-150-4045")
                .build();

        Mockito.when(userService.getUserById(2001L)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", 2001L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
