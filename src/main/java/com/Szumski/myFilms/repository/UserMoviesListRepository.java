package com.Szumski.myFilms.repository;


import com.Szumski.myFilms.model.databaseModels.UserMoviesList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMoviesListRepository extends MongoRepository<UserMoviesList, Long> {
}
