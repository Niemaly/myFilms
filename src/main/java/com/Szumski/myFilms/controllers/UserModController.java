package com.Szumski.myFilms.controllers;

import com.Szumski.myFilms.model.databaseModels.MovieModel;
import com.Szumski.myFilms.model.databaseModels.User;
import com.Szumski.myFilms.model.UserMovie;
import com.Szumski.myFilms.service.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserModController {

  @Autowired
  private UserMoviesService userMoviesService;


  @RequestMapping(value = "/add_movie", method = RequestMethod.POST)
  public ResponseEntity<MovieModel> addFilmToUser(@RequestParam UserMovie userMovie, @AuthenticationPrincipal User user){


      boolean isMovieExist = userMoviesService.getAllMovies(user.getIdFilmList()).stream().anyMatch(element-> element.getId()==userMovie.getId());

      if (isMovieExist){
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }
      try {
      } catch (NullPointerException e){
          return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
      }

      return null;
  }

    @RequestMapping(value = "/delete_movie", method = RequestMethod.GET)
    public List<UserMovie> deleteMovieUser( @AuthenticationPrincipal User user){
        int movieId =111;
        userMoviesService.deleteMovieFromUser(movieId);
        return userMoviesService.getAllMovies(user.getIdFilmList());

      }



    }