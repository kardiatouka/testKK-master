package com.example.test.model;


import com.example.test.constraints.BirthDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@ApiModel(description = "Class representing a user in the application.")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User Name is required")
    private String username;

    @NotNull(message = "Birth date is required")
    @Past(message = "Date of birth must be a date in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @BirthDate(message = "The birth date must be greater or equal than 18")
    private LocalDate birthdate;

    @NotNull(message = "Country is required")
    @Pattern(regexp = "France", message = "Country: Only French residents are allowed")
    private String country;

    @ApiModelProperty(notes = "Phone number of the user.", required = false, position = 5,
            example = "624-822-1111")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message = "Mobile number is invalid, valid format is XXX-XXX-XXXX")
    private String phoneNumber;

    @Pattern(regexp = "(MALE|FEMALE|OTHER)$", message = "Gender must be MALE, FEMALE or OTHER")
    private String gender;
}
