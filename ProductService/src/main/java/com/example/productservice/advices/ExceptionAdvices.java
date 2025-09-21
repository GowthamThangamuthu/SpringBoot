package com.example.productservice.advices;


import com.example.productservice.dtos.ErrorResponseDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponseDto handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setStatus("Please, Try with the correct Product ID");
        return errorResponseDto;
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRuntimeException (RuntimeException e){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setStatus("ERROR");

        return errorResponseDto;
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseDto handleException(Exception e){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setStatus("ERROR");

        return errorResponseDto;
    }
}
