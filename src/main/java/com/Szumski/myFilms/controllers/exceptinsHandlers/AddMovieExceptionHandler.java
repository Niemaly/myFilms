package com.Szumski.myFilms.controllers.exceptinsHandlers;

import com.Szumski.myFilms.exceptions.AddMovieTroubleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddMovieExceptionHandler {

    @ExceptionHandler(value = AddMovieTroubleException.class)
    public ResponseEntity<Object> exception(AddMovieTroubleException exception) {

        return new ResponseEntity<>("Trouble with adding Movie.\nPlease contact with our support service!", HttpStatus.NOT_FOUND);
    }

}
