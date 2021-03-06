package com.Szumski.myFilms.controllers.exceptinsHandlers;

import com.Szumski.myFilms.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotExistExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {

        return new ResponseEntity<>("User not Found :O !!", HttpStatus.NOT_FOUND);

    }

}
