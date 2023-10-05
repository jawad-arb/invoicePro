package com.example.invoicepro.services;

import com.example.invoicepro.dto.UserDTO;
import com.example.invoicepro.model.User;

public interface UserService {

    /**
     *
     * create a user in DB and return a UserDTO
     * UserDTO is a user without password
     * @Return UserDTO
     *
     * **/
    UserDTO create(User user);
    UserDTO getUserById(Long id);













}
