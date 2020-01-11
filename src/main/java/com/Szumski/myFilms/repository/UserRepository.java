package com.Szumski.myFilms.repository;

import com.Szumski.myFilms.model.databaseModels.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String userName);


}
