package com.Szumski.myFilms.controllers;

import com.Szumski.myFilms.model.*;

import com.Szumski.myFilms.parsers.FilmParser;
import com.Szumski.myFilms.parsers.UrlGenerator;
import com.Szumski.myFilms.pojo.MovieQuery;
import com.Szumski.myFilms.pojo.tmdbSearchListspojo.AllList;
import com.Szumski.myFilms.pojo.tmdbpojo.MovieTMDb;
import com.Szumski.myFilms.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController("/")
public class ReqToTMDb {

    @Value("${api.key}")
    private String apiKey;

    @Value("${language.en}")
    private String language;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieRepository movieRepository;




    @ResponseBody
    @RequestMapping("/upcoming")
    public ResponseAllFilms getUpcoming(){

        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        AllList allList = restTemplate.getForObject(
                urlGenerator.getUpcoming("1"),
                AllList.class
        );

        return new ResponseAllFilms(allList);
    }

    @ResponseBody
    @RequestMapping("/now_playing")
    public ResponseAllFilms getNowPlaying(){
        String page;
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);
        page="1";

        AllList allList = restTemplate.getForObject(
                urlGenerator.getNowPlaying(page),
                AllList.class
        );

        return new ResponseAllFilms(allList);
    }
    @ResponseBody
    @RequestMapping("/popular")
    public ResponseAllFilms getPopular(){
        String page;
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);
        page="37";

        AllList allList = restTemplate.getForObject(
                urlGenerator.getPopular(page),
                AllList.class
        );

        return new ResponseAllFilms(allList);
    }

    @ResponseBody
    @RequestMapping("/similar_movie/{movieId}")
    public ResponseAllFilms getSimilarMovies(@PathVariable("movieId") String movieId){
        String page;
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);
        page="1";

        AllList allList = restTemplate.getForObject(
                urlGenerator.getSimilarMovies(movieId, page),
                AllList.class
        );

        return new ResponseAllFilms(allList);
    }



    @ResponseBody
    @RequestMapping("/recommendations/{movieId}/{page}")
    public ResponseAllFilms getRecommendations(@PathVariable("movieId") String movieId, @PathVariable("page") String page){
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        AllList allList = restTemplate.getForObject(
                urlGenerator.getRecommendations(movieId, page),
                AllList.class
        );

        return new ResponseAllFilms(allList);
    }

    @ResponseBody
    @RequestMapping("/movie_details/{movieId}")
    public MovieTMDb getMovieInformation(@PathVariable("movieId") String movieId){
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        MovieTMDb movie = restTemplate.getForObject(
                urlGenerator.getMovieById(movieId),
                MovieTMDb.class
        );

        movieRepository.save(FilmParser.parseToMovieModel(movie));
        return movie;
    }

    @ResponseBody
    @RequestMapping("/search_movies")
    public ResponseAllFilms searchMovie(@RequestParam MovieQuery movieQuery){
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        AllList allList = restTemplate.getForObject(
                urlGenerator.searchMovie(movieQuery),
                AllList.class
        );
        ResponseAllFilms responseAllFilms = new ResponseAllFilms(allList);

        responseAllFilms.getListOfMovies().forEach(movie ->{
            if (!movieRepository.findById(movie.getId()).isPresent()){
                movieRepository.save(movie);
            } else if (movieRepository.findById(movie.getId()).get().getRating().equals(movie.getRating())){
                movieRepository.findById(movie.getId()).get().setRating(movie.getRating());
            }
                });

        return responseAllFilms;
    }


}
