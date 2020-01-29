package com.Szumski.myFilms.controllers;

import com.Szumski.myFilms.controllers.operationsForControllers.AllListReturner;
import com.Szumski.myFilms.model.frontendComunication.ResponseAllFilms;

import com.Szumski.myFilms.model.parsers.*;
import com.Szumski.myFilms.model.pojo.tmdbSearchListspojo.AllList;
import com.Szumski.myFilms.model.pojo.tmdbpojo.MovieTMDb;
import com.Szumski.myFilms.repository.MovieRepository;
import com.Szumski.myFilms.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController()
public class ReqToTMDb {

    @Value("${api.key}")
    private String apiKey;

    @Value("${language.en}")
    private String language;

    private RestTemplate restTemplate;
    private MovieRepository movieRepository;
    private MoviesService moviesService;

    @Autowired
    public ReqToTMDb(RestTemplate restTemplate, MovieRepository movieRepository, MoviesService moviesService)  {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
        this.moviesService=moviesService;
    }

    @ResponseBody
    @RequestMapping(value = "/upcoming", method = RequestMethod.POST)
    public ResponseAllFilms getUpcoming(@RequestParam(required = false, defaultValue = "1") String page){



        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);
        AllList allList = AllListReturner.returnAllList(restTemplate, urlGenerator.getUpcoming(page));

        moviesService.saveCurrentRating(allList);

        return new ResponseAllFilms(allList);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/now_playing")
    public ResponseAllFilms getNowPlaying(@RequestParam(required = false, defaultValue = "1") String page){

        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);


        AllList allList = AllListReturner.returnAllList(restTemplate, urlGenerator.getNowPlaying(page));

        moviesService.saveCurrentRating(allList);

        return new ResponseAllFilms(allList);
    }

    @ResponseBody
    @RequestMapping(value = "/popular", method = RequestMethod.POST)
    public ResponseAllFilms getPopular(@RequestParam(required = false, defaultValue = "1") String page){

        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        AllList allList = AllListReturner.returnAllList(restTemplate , urlGenerator.getPopular(page));

        moviesService.saveCurrentRating(allList);

        return new ResponseAllFilms(allList);
    }

    @ResponseBody
    @RequestMapping(value = "/top_rated", method = RequestMethod.POST)
    public ResponseAllFilms getTopRated(@RequestParam(required = false, defaultValue = "1") String page){

        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        AllList allList = AllListReturner.returnAllList(restTemplate , urlGenerator.getTopRated(page));

        moviesService.saveCurrentRating(allList);

        return new ResponseAllFilms(allList);
    }



    @ResponseBody
    @RequestMapping(method = RequestMethod.POST , value = "/similar_movies")
    public ResponseAllFilms getSimilarMovies(@RequestParam String movieId, @RequestParam(required = false, defaultValue = "1") String page){

        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        AllList allList = AllListReturner.returnAllList(restTemplate, urlGenerator.getSimilarMovies(movieId, page));

        moviesService.saveCurrentRating(allList);

        return new ResponseAllFilms(allList);
    }



    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/recommendations")
    public ResponseAllFilms getRecommendations(@RequestParam String movieId, @RequestParam(required = false, defaultValue = "1") String page){
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        AllList allList = AllListReturner.returnAllList(restTemplate, urlGenerator.getRecommendations(movieId,page));

        moviesService.saveCurrentRating(allList);

        return new ResponseAllFilms(allList);
    }

    @ResponseBody
    @RequestMapping(value = "/movie_details", method = RequestMethod.POST)
    public MovieTMDb getMovieInformation(@RequestParam("movieId") String movieId){
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        MovieTMDb movie = restTemplate.getForObject(
                urlGenerator.getMovieById(movieId),
                MovieTMDb.class
        );

        movieRepository.save(FilmParser.parseToMovieModel(movie));

        return movie;
    }




}
