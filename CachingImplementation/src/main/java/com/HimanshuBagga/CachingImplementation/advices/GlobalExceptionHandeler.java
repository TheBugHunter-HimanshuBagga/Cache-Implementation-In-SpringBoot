package com.HimanshuBagga.CachingImplementation.advices;

import com.HimanshuBagga.CachingImplementation.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> ResourceNotFoundException(ResourceNotFoundException exception){
        ApiError apiError = new ApiError(exception.getLocalizedMessage() , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError , HttpStatus.NOT_FOUND);
    }

}
