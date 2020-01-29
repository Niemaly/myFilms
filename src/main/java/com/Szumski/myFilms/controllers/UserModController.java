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




@RestController
public class UserModController {


  private UserMoviesService userMoviesService;
  private MovieRepository movieRepository;

    @Autowired
    public UserModController(UserMoviesService userMoviesService, MovieRepository movieRepository) {
        this.userMoviesService = userMoviesService;
        this.movieRepository = movieRepository;
    }

    @RequestMapping(value = "/add_movie", method = RequestMethod.POST)
    public ResponseForUserMovies addFilmToUser(@RequestParam Long id,
          @RequestParam(required = false, defaultValue = "") String comment,
          @RequestParam boolean status,
          @RequestParam(required = false, defaultValue = "-1.0") Double userRating,
          @AuthenticationPrincipal User user)  {

        System.out.println("adding movie");

        UserMovie userMovie = new UserMovie();
        if (userRating>0) {
            userMovie.setRating(userRating);
        }

        userMovie.setComment(comment);
        userMovie.setId(id);
        userMovie.setStatus(status);

        System.out.println("movie parameters are rdy");

        System.out.println(userMovie.toString());



      try {
          boolean isUserMovieExistInUserMovies = userMoviesService.checkIsMovieExistInUser(user, userMovie.getId());
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


    @RequestMapping(value = "/delete_movie", method = RequestMethod.POST)
    public Status deleteMovieUser(@AuthenticationPrincipal User user, @RequestParam String movieId){

        System.out.println(user.getUsername()+ " " + movieId);
        System.out.println(userMoviesService.checkIsMovieExistInUser(user, Long.parseLong(movieId)));

        if (userMoviesService.checkIsMovieExistInUser(user, Long.parseLong(movieId))) {

            userMoviesService.deleteMovieFromUser(user, Long.parseLong(movieId));

            return new Status(true);

        } else{

            return new Status(false);

        }

      }

    }