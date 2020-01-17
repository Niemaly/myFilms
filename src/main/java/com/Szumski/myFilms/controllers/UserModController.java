package com.Szumski.myFilms.controllers;

import com.Szumski.myFilms.exceptions.AddMovieTroubleException;
import com.Szumski.myFilms.model.databaseModels.User;
import com.Szumski.myFilms.model.databaseModels.UserMovie;
import com.Szumski.myFilms.model.frontendComunication.ResponseForUserMovies;
import com.Szumski.myFilms.repository.MovieRepository;
import com.Szumski.myFilms.service.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private MovieRepository movieRepository;

  @RequestMapping(value = "/add_movie", method = RequestMethod.POST)
  public ResponseForUserMovies addFilmToUser(@RequestParam UserMovie userMovie, @AuthenticationPrincipal User user)  {

      try {
          boolean isUserMovieExistInUserMovies = userMoviesService.getAllMovies(user.getIdFilmList()).stream().anyMatch(element-> element.getId()==userMovie.getId());
          if (isUserMovieExistInUserMovies){
              return new ResponseForUserMovies(movieRepository.findById(userMovie.getId()).get(),userMovie);
          } else {
              userMoviesService.saveMovieToUser(userMovie,user);
              System.out.println("movie is saved to user "+ user.getUsername()+ "  -- movie -- \n" + userMovie.toString());
          }


      } catch (Exception e){
        throw new AddMovieTroubleException();
      }

      return null;

  }

    @RequestMapping(value = "/delete_movie", method = RequestMethod.GET)
    public List<UserMovie> deleteMovieUser( @AuthenticationPrincipal User user){
        Long movieId =111L;

        System.out.println("movie is deleted from user " + user.getUsername() + " -- movie -- \n" + userMoviesService.getUserMoviesListRepository().findById(movieId));

        userMoviesService.deleteMovieFromUser(movieId);


        return userMoviesService.getAllMovies(user.getIdFilmList());

      }



    }