package com.shopeasy.user_microservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopeasy.user_microservices.dto.ApiResponse;
import com.shopeasy.user_microservices.dto.UserDTO;
import com.shopeasy.user_microservices.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/users_microservice")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/userDetails")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getUserDetails(){
        List<UserDTO> users = userService.getUserDetails();
        ApiResponse<List<UserDTO>> response = new ApiResponse<List<UserDTO>>("Users fetched successfully",HttpStatus.OK.value(), users);
      return ResponseEntity.ok(response);
    }

    @PostMapping(value ="/addUser")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserDTO userDTO){
      UserDTO user = userService.createUser(userDTO);
      if(user!= null){
         ApiResponse<UserDTO> response = new ApiResponse<UserDTO>("Users fetched successfully",HttpStatus.OK.value(), user);
        return ResponseEntity.ok(response);
      }
      ApiResponse<UserDTO> response =
            new ApiResponse<>(
                    "User creation failed",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response);
    }
}
