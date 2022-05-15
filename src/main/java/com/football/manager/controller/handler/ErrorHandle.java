package com.football.manager.controller.handler;

import com.football.manager.exception.TransferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ErrorHandle {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("handleMethodArgumentNotValidException: exception {}", message);
        return message;
    }
    @ExceptionHandler(TransferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTransferException(TransferException ex) {
        log.error("TransferException: exception {}", ex.getMessage());
        return ex.getMessage();
    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoSuchElementException(NoSuchElementException ex) {
        log.error("NoSuchElementException: exception {}", ex.getMessage());
        return ex.getMessage();
    }
}
