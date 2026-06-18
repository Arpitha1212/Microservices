package com.shopeasy.user_microservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopeasy.user_microservices.dto.ApiResponse;
import com.shopeasy.user_microservices.dto.CreateUserRequest;
import com.shopeasy.user_microservices.dto.UpdateUserRequest;
import com.shopeasy.user_microservices.dto.UserDTO;
import com.shopeasy.user_microservices.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody CreateUserRequest userDTO){
      UserDTO user = userService.createUser(userDTO);
        ApiResponse<UserDTO> response = new ApiResponse<UserDTO>("Users created successfully",HttpStatus.OK.value(), user);
        return ResponseEntity.ok(response);
    }    

    @PatchMapping(value ="/{id}/updateUser")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest userDTO){
         UserDTO user = userService.updateUser(id,userDTO); 
         ApiResponse<UserDTO> response = new ApiResponse<UserDTO>("Users updated successfully",HttpStatus.OK.value(), user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        ApiResponse<Void> response = new ApiResponse<>("User deleted successfully", HttpStatus.OK.value(), null);
        return ResponseEntity.ok(response);
    }

}
