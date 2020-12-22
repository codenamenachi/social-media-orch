package app.controller;

import app.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler
    private ResponseEntity<?> handleExceptions(ServiceException e){

        return new ResponseEntity<>(e.getMessageDetail(), e.getReasonCode());

    }
}
