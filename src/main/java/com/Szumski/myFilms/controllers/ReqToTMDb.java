package com.Szumski.myFilms.controllers;

import com.Szumski.myFilms.model.frontendComunication.MovieQuery;
import com.Szumski.myFilms.model.frontendComunication.ResponseAllFilms;

import com.Szumski.myFilms.model.parsers.*;
import com.Szumski.myFilms.model.pojo.tmdbSearchListspojo.AllList;
import com.Szumski.myFilms.model.pojo.tmdbpojo.MovieTMDb;
import com.Szumski.myFilms.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
    private AllList allList;


    @Autowired
    public ReqToTMDb(RestTemplate restTemplate, MovieRepository movieRepository)  {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
    }



    @ResponseBody
    @RequestMapping("/upcoming")
    public ResponseAllFilms getUpcoming(){

        String page="1";

        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);
        AllList allList = returnAllList(urlGenerator.getUpcoming(page));


        return new ResponseAllFilms(allList);
    }

    @ResponseBody
    @RequestMapping("/now_playing")
    public ResponseAllFilms getNowPlaying(){
        String page;
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);
        page="1";

        AllList allList = returnAllList(urlGenerator.getNowPlaying(page));

        return new ResponseAllFilms(allList);
    }
    @ResponseBody
    @RequestMapping("/popular")
    public ResponseAllFilms getPopular(){
        String page;
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);
        page="37";

        AllList allList = returnAllList(urlGenerator.getPopular(page));

        return new ResponseAllFilms(allList);
    }

    @ResponseBody
    @RequestMapping("/similar_movie/{movieId}")
    public ResponseAllFilms getSimilarMovies(@PathVariable("movieId") String movieId){
        String page;
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);
        page="1";

        AllList allList = returnAllList(urlGenerator.getSimilarMovies(movieId, page));

        return new ResponseAllFilms(allList);
    }



    @ResponseBody
    @RequestMapping("/recommendations/{movieId}/{page}")
    public ResponseAllFilms getRecommendations(@PathVariable("movieId") String movieId, @PathVariable("page") String page){
        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        AllList allList = returnAllList(urlGenerator.getRecommendations(movieId,page));

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
    @RequestMapping(value = "/search_movies", method = RequestMethod.POST)
    public ResponseAllFilms searchMovie(@RequestParam Integer page,
                                        @RequestParam Boolean include_adult,
                                        @RequestParam String  region,
                                        @RequestParam String  year,
                                        @RequestParam String  primary_release_year,
                                        @RequestParam String  query) {

        MovieQuery movieQuery = new MovieQuery();
            movieQuery.setPage(page);
            movieQuery.setInclude_adult(include_adult);
            movieQuery.setRegion(region);
            movieQuery.setYear(year);
            movieQuery.setPrimary_release_year(primary_release_year);
            movieQuery.setQuery(query);

        System.out.println(movieQuery.toString());

        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

        AllList allList = returnAllList(urlGenerator.searchMovie(movieQuery));

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

    private AllList returnAllList(String string){


        return restTemplate.getForObject(
                string,
                AllList.class
        );
    }



    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Object exception(MissingServletRequestParameterException exception)  {

        return new MovieQuery(1, true, "Poland", "2000", "2001", "Terminator");

    }





}
