package com.Szumski.myFilms.model.databaseModels;

import com.Szumski.myFilms.service.UserMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class AutoincrementId {

    private UserMoviesService userMoviesService;

    @Autowired
    public AutoincrementId(UserMoviesService userMoviesService) {
        this.userMoviesService = userMoviesService;
    }

    public AutoincrementId() {
    }

    public Long movieListRepo() {
        try {
            return userMoviesService.getUserMoviesListRepository().count();
        }catch (NullPointerException e){
            return 0L;
        }

    }
}
