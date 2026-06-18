package com.shopeasy.user_microservices.exception;

public class UserAlreadyExistsException extends RuntimeException {
     public UserAlreadyExistsException(String message) { super(message); }
}
