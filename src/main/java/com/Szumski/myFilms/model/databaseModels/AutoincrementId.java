package com.Szumski.myFilms.model.databaseModels;

import com.Szumski.myFilms.repository.UserMoviesListRepository;
import com.Szumski.myFilms.repository.UserRepository;
import com.Szumski.myFilms.service.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class AutoincrementId {

    private UserRepository userRepository;


    public AutoincrementId(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Long createIdOfUserMoviesList() {
        try {
            return userRepository.count();
        }catch (NullPointerException e){
            return 0L;
        }

    }
}
