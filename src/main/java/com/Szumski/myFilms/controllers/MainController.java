package com.Szumski.myFilms.controllers;

import com.Szumski.myFilms.exceptions.UserNotFoundException;
import com.Szumski.myFilms.model.UserInformation;
import com.Szumski.myFilms.model.frontendComunication.ResponseForUserMovies;
import com.Szumski.myFilms.model.databaseModels.User;
import com.Szumski.myFilms.repository.MovieRepository;
import com.Szumski.myFilms.service.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserMoviesService userMoviesService;



    @RequestMapping(value = "/user_details" , method = RequestMethod.GET, produces = "application/json")
    public UserInformation mainController(@AuthenticationPrincipal User user){

        if (user == null){
            throw new UserNotFoundException();
        }

        UserInformation userInformation = new UserInformation(user);
        return userInformation;

    }

    @RequestMapping(value = "/user_movies" , method = RequestMethod.GET, produces = "application/json")
    public List<ResponseForUserMovies> getUserMovies(@AuthenticationPrincipal User user){
        List<ResponseForUserMovies> listOfFilms = new ArrayList<>();


        // FROM USER REPO TAKE USER by ID -> eq to current user -> take data
        userMoviesService.getAllMovies(user.getIdFilmList()).stream().forEach(element -> {
                    listOfFilms.add(new ResponseForUserMovies(movieRepository.findById((long)element.getId()).orElseThrow(RuntimeException::new), element));
                });

        return listOfFilms;
    }




}
