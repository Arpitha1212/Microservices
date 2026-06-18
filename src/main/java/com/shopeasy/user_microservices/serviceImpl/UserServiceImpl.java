package com.shopeasy.user_microservices.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopeasy.user_microservices.dto.CreateUserRequest;
import com.shopeasy.user_microservices.dto.UpdateUserRequest;
import com.shopeasy.user_microservices.dto.UserDTO;
import com.shopeasy.user_microservices.entity.User;
import com.shopeasy.user_microservices.exception.UserAlreadyExistsException;
import com.shopeasy.user_microservices.exception.UserNotFoundException;
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
       return userDetails.stream()
            .map(user -> new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPasswordHash()
            ))
            .toList();
    }

    @Override
    public UserDTO createUser(CreateUserRequest request) {
        if(userRepository.existsByEmail(request.email())){
          throw new RuntimeException("User already exists with email: " + request.email());
        }
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(request.password());

        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPasswordHash());
    }

    @Override
    public UserDTO updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        user.setName(request.name());
        if (!request.email().equalsIgnoreCase(user.getEmail())) {
            if (userRepository.existsByEmail(request.email())) {
                throw new UserAlreadyExistsException("User already exists with email: " + request.email());
            }
            user.setEmail(request.email());
        }
        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getPasswordHash());
    }

    @Override
    public Boolean deleteUser(Long id){
    User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
      userRepository.delete(user);
      return true;
    }
  }
