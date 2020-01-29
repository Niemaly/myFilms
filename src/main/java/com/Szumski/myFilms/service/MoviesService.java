package com.Szumski.myFilms.service;
import com.Szumski.myFilms.model.parsers.*;
import com.Szumski.myFilms.exceptions.MovieIsNotExistInDatabaseException;
import com.Szumski.myFilms.model.databaseModels.MovieModel;
import com.Szumski.myFilms.model.pojo.tmdbSearchListspojo.AllList;
import com.Szumski.myFilms.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MoviesService {

    MovieRepository movieRepository;

    public MoviesService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }



    public void saveCurrentRating(AllList allList){

        allList.getResults().stream().forEach(element->{

            if (movieRepository.findById(element.getId()).isPresent()){
                if (element.getVote_average()!=movieRepository.findById(element.getId()).get().getRating()){

                    MovieModel movieModel = new MovieModel();

                    movieModel.setId(element.getId());
                    movieModel.setDateOfRelease(element.getRelease_date());
                    movieModel.setRating(element.getVote_average());
                    movieModel.setSrc(element.getPoster_path());
                    movieModel.setTitle(element.getTitle());
                    movieModel.setOverview(element.getOverview());
                    movieModel.setGenreIdList(element.getGenre_ids());

                    updateMovie(element.getId(), movieModel);
                }
            }
            else {

                MovieModel movieModel = new MovieModel();

                movieModel.setId(element.getId());
                movieModel.setDateOfRelease(element.getRelease_date());
                movieModel.setRating(element.getVote_average());
                movieModel.setSrc(element.getPoster_path());
                movieModel.setTitle(element.getTitle());
                movieModel.setOverview(element.getOverview());
                movieModel.setGenreIdList(element.getGenre_ids());

                movieRepository.save(movieModel);

            }
        });

    }



    public void updateMovie(Long id, MovieModel movie){

            if (!movieRepository.findById(id).isPresent()){
                throw new MovieIsNotExistInDatabaseException();
            }

            movieRepository.deleteById(id);
            movieRepository.save(movie);

    }



}
