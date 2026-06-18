package com.shopeasy.user_microservices.service;

import java.util.List;

import com.shopeasy.user_microservices.dto.CreateUserRequest;
import com.shopeasy.user_microservices.dto.UpdateUserRequest;
import com.shopeasy.user_microservices.dto.UserDTO;

public interface UserService {

    List<UserDTO> getUserDetails();

    UserDTO createUser(CreateUserRequest user);

    UserDTO updateUser(Long id,UpdateUserRequest user);

    Boolean deleteUser(Long id);

}
