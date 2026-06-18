package com.shopeasy.user_microservices.dto;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record  ErrorResponseDTO(String apiPath,HttpStatus errorCode,String errorMessage,LocalDateTime errorTime) {

}