package com.example.invoicepro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {
    private Long id;
    @NotEmpty(message = "first name most be not empty")
    private String firstName;
    @NotEmpty(message = "last name most be not empty")
    private String lastName;
    @NotEmpty(message = "email most be not empty")
    @Email(message = "Invalid Email , please enter a valid email address")
    private String email;
    @NotEmpty(message = "password name most be not empty")
    private String password;
    private String address;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;
    private Boolean enabled;
    private Boolean isNotLocked;
    private Boolean isUsingMfa;
    private LocalDateTime createdAt;




}
