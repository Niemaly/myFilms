package com.Szumski.myFilms.repository;

import com.Szumski.myFilms.model.MovieModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<MovieModel, Long> {
}
