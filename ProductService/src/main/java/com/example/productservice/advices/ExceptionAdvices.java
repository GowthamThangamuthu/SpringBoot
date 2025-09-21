package com.example.productservice.advices;


import com.example.productservice.dtos.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionAdvices {

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
