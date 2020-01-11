package com.Szumski.myFilms.configuration;

import com.Szumski.myFilms.model.databaseModels.MovieModel;
import com.Szumski.myFilms.model.databaseModels.User;
import com.Szumski.myFilms.model.UserMovie;
import com.Szumski.myFilms.repository.MovieRepository;
import com.Szumski.myFilms.service.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Init {

    private final WebSecurityConfig webSecurityConfig;
    private UserMoviesService userMoviesService;
    private MovieRepository movieRepository;

    @Autowired
    public Init(WebSecurityConfig webSecurityConfig, UserMoviesService userMoviesService, MovieRepository movieRepository) {
        this.userMoviesService = userMoviesService;
        this.webSecurityConfig = webSecurityConfig;
        this.movieRepository = movieRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void setSomeUsers() {
        User user1 = new User("user", webSecurityConfig.passwordEncoder().encode("user"), "USER");
        User user2 = new User("u", webSecurityConfig.passwordEncoder().encode("user"), "USER");
        User user3 = new User("useruser", webSecurityConfig.passwordEncoder().encode("user"), "USER");

        System.out.println(user1.getUsername());
        System.out.println(user2.getUsername());
        System.out.println(user3.getUsername());

        userMoviesService.getUserRepository().deleteAll();

        userMoviesService.setUser(user1);
        userMoviesService.createNewUser(user1);

        userMoviesService.setUser(user2);
        userMoviesService.createNewUser(user2);

        userMoviesService.setUser(user3);
        userMoviesService.createNewUser(user3);


        List<UserMovie> userMoviesList1 = userMoviesService.getAllMovies(user1.getIdFilmList());
        UserMovie userFilm = new UserMovie(111L);
        UserMovie userFilm2 = new UserMovie(222L);

        userFilm2.setRating(7.3);
        userFilm.setRating(5.2);
        userFilm.setStatus(true);
        userFilm.setComment("this was awesome film man");
        userMoviesList1.add(userFilm);
        userMoviesList1.add(userFilm2);
        userMoviesService.saveMovieToUser(userFilm, user1);
        userMoviesService.saveMovieToUser(userFilm2, user1);

        System.out.println(userMoviesService.getAllMovies(user1.getIdFilmList()));
        System.out.println(userMoviesService.getAllMovies(user2.getIdFilmList()));

        movieRepository.deleteAll();


        movieRepository.saveAll(mockMovies());

        movieRepository.findAll().stream().forEach(element -> {
            System.out.println(element.toString());
        });

    }

    private List<MovieModel> mockMovies() {
        List<MovieModel> list = new ArrayList<MovieModel>();

        MovieModel movieModel = new MovieModel();
        movieModel.setTitle("Berlin: Die Sinfonie der Großstadt");
        movieModel.setRating(7.8);
        movieModel.setSrc("/ePGMsItIMHSOvZkCJ6rnl4v64Sq.jpg");
        movieModel.setId(222L);
        movieModel.setGenreIdList(new ArrayList<Integer>());
        movieModel.setDateOfRelease("1927-09-23");


        MovieModel movieModel2 = new MovieModel();
        movieModel2.setTitle("Scarface");
        movieModel2.setRating(8.1);
        movieModel2.setSrc("/ePGMsItIMHSOvZkCJ6rnl4v64Sq.jpg");
        movieModel2.setId(111L);
        movieModel2.setGenreIdList(new ArrayList<Integer>());
        movieModel2.setDateOfRelease("1983-12-08");

        list.add(movieModel);
        list.add(movieModel2);

        return list;

    }
}