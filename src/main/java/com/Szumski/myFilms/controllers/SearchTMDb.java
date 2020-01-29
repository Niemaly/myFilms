package com.Szumski.myFilms.controllers;

import com.Szumski.myFilms.controllers.operationsForControllers.AllListReturner;
import com.Szumski.myFilms.model.frontendComunication.MovieQuery;
import com.Szumski.myFilms.model.frontendComunication.ResponseAllFilms;
import com.Szumski.myFilms.model.parsers.UrlGenerator;
import com.Szumski.myFilms.model.pojo.tmdbSearchListspojo.AllList;
import com.Szumski.myFilms.repository.MovieRepository;
import com.Szumski.myFilms.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Null;

@RestController()
public class SearchTMDb  {

    @Value("${api.key}")
    private String apiKey;

    @Value("${language.en}")
    private String language;

    private RestTemplate restTemplate;
    private MovieRepository movieRepository;
    private MoviesService moviesService;


    @Autowired
    public SearchTMDb(RestTemplate restTemplate, MovieRepository movieRepository, MoviesService moviesService)  {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
        this.moviesService = moviesService;
    }

    @ResponseBody
    @RequestMapping(value = "/search_movies", method = RequestMethod.POST)
    public ResponseAllFilms searchMovie(@RequestParam Integer page,
                                        @RequestParam(required = false, defaultValue = "false") Boolean include_adult,
                                        @RequestParam String region,
                                        @RequestParam String year,
                                        @RequestParam String primary_release_year,
                                        @RequestParam String query) {

        long startTime = System.currentTimeMillis();


        MovieQuery movieQuery = new MovieQuery();
        movieQuery.setPage(page);
        movieQuery.setInclude_adult(include_adult);
        movieQuery.setRegion(region);
        movieQuery.setYear(year);
        movieQuery.setPrimary_release_year(primary_release_year);
        movieQuery.setQuery(query);

        UrlGenerator urlGenerator = new UrlGenerator(apiKey, language);

       System.out.println(urlGenerator.searchMovie(movieQuery));

        AllList allList = AllListReturner.returnAllList(restTemplate, urlGenerator.searchMovie(movieQuery));

        System.out.println("\n\nTime Connecting To API \n"+ (System.currentTimeMillis()-startTime)+ "\n\n");

        startTime = System.currentTimeMillis();

        moviesService.saveCurrentRating(allList);

        ResponseAllFilms responseAllFilms = new ResponseAllFilms(allList);

        responseAllFilms.getListOfMovies().forEach(movie -> {
            if (movieRepository.findById(movie.getId()).isPresent()) {
                movieRepository.save(movie);
            }
        });

        System.out.println("\n\nTime SAVING IN DATABASE  \n"+ (System.currentTimeMillis()-startTime) + "\n\n");

        return responseAllFilms;
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Object exceptionHandle(MissingServletRequestParameterException exception) {

        return new MovieQuery(1, true, "USA", "2000", "2001", "Terminator");

    }
}