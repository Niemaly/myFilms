package com.Szumski.myFilms.controllers.exceptinsHandlers;

import com.Szumski.myFilms.exceptions.MovieIsNotExistInDatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieNotFoundExceptionHandler {

    @ExceptionHandler(value = MovieIsNotExistInDatabaseException.class)
    public String exception(MovieIsNotExistInDatabaseException exception) {
        return "{\"status\":true}";
    }
}
