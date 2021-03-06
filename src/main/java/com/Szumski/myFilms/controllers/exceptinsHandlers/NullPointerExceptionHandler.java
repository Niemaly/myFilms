package com.Szumski.myFilms.controllers.exceptinsHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NullPointerExceptionHandler {


    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> exception(NullPointerException exception) {

        return new ResponseEntity<>("NULL -> check wtf is wrong... plz dev make ur job...", HttpStatus.NOT_FOUND);
    }

}
