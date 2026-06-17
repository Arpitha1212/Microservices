package com.shopeasy.user_microservices.dto;

public record ApiResponse<T>(String message,int status,T data ) {

}
