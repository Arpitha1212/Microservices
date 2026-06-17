package com.shopeasy.user_microservices.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopeasy.user_microservices.dto.UserDTO;
import com.shopeasy.user_microservices.entity.User;
import com.shopeasy.user_microservices.repository.UserRepository;
import com.shopeasy.user_microservices.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getUserDetails() {
      List<User> userDetails =  userRepository.findAll();
      return userDetails.stream().map(user-> new UserDTO(user.getName(),user.getEmail(),user.getPasswordHash())).toList();
    }

    @Override
    public UserDTO createUser(UserDTO user){   
      User createUser = mapDTOtoEntity(user);
      User savedUser = userRepository.save(createUser);
      if(savedUser!= null){
       user = new UserDTO(savedUser.getName(),savedUser.getEmail(),savedUser.getPasswordHash());
      }
      return user;
    }

    public User mapDTOtoEntity(UserDTO userDTO){
      User user = new User();
      user.setName(userDTO.name());
      user.setEmail(userDTO.email());
      user.setPasswordHash(userDTO.passwordHash());
      return user;
    }
}
