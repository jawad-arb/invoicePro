package com.example.invoicepro.services.implementation;

import com.example.invoicepro.dto.UserDTO;
import com.example.invoicepro.dtomapper.UserDTOMapper;
import com.example.invoicepro.model.User;
import com.example.invoicepro.repositories.UserRepository;
import com.example.invoicepro.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;
    /**
     * @Return UserDTO
     * @Parameters User
     * @Doing map from user to userDTO and create user
     * in DB and Return UserDTO object
     * */
    @Override
    public UserDTO create(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.get(id);
    }
}
