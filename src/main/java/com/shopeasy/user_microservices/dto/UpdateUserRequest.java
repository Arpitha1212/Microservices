package com.shopeasy.user_microservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @NotBlank @Size(max = 25) String name,
    @NotBlank @Email String email
) {}