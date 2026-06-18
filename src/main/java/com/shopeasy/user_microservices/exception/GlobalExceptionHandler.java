package com.shopeasy.user_microservices.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.shopeasy.user_microservices.exception.UserNotFoundException;

import com.shopeasy.user_microservices.dto.ErrorResponseDTO;

import java.util.List;

import com.shopeasy.user_microservices.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler 
 {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> MethodArgumentNotValidException(MethodArgumentNotValidException ex){
       Map<String, String> errors = new HashMap<>();
       List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
       for(FieldError error : fieldError){
         errors.put(error.getField(),error.getDefaultMessage());
       }
       return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    } 

     @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex,WebRequest request){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
            request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getMessage(),LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> handleNullPointerException(Exception ex,WebRequest request){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
            request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR,
            "A NullPointerException occured Due to: "+ex.getMessage(),LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(
            RuntimeException ex,
            WebRequest request) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(ex.getMessage(), HttpStatus.CONFLICT.value(), null));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null));
    }
}
