package com.Szumski.myFilms.parsers;

import com.Szumski.myFilms.model.MovieModel;
import com.Szumski.myFilms.pojo.tmdbSearchListspojo.ListElementMovie;
import com.Szumski.myFilms.pojo.tmdbpojo.MovieTMDb;

import java.util.ArrayList;
import java.util.List;

public class FilmParser {

    public static MovieModel parseToMovieModel(MovieTMDb movieTMDb){
        MovieModel movieModel = new MovieModel();
        movieModel.setId(movieTMDb.getId());
        movieModel.setDateOfRelease(movieTMDb.getRelease_date());
        movieModel.setRating(movieTMDb.getVote_average());
        movieModel.setSrc(movieTMDb.getPoster_path());

        List genreList = new ArrayList();

        movieTMDb.getGenres().stream().forEach(genre -> genreList.add(genre.getId()));
        movieModel.setGenreIdList(genreList);

        return movieModel;
    }


    public static MovieModel parseToMovieModel(ListElementMovie element) {
        MovieModel movieModel = new MovieModel();
        movieModel.setId(element.getId());
        movieModel.setDateOfRelease(element.getRelease_date());
        movieModel.setRating(element.getVote_average());
        movieModel.setSrc(element.getPoster_path());
        movieModel.setTitle(element.getTitle());

        movieModel.setGenreIdList(element.getGenre_ids());

        return movieModel;
    }
}
