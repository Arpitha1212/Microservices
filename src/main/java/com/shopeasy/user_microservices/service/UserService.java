package com.shopeasy.user_microservices.service;

import java.util.List;

import com.shopeasy.user_microservices.dto.UserDTO;

public interface UserService {

    List<UserDTO> getUserDetails();

    UserDTO createUser(UserDTO user);

}
