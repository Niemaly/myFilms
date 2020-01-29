package com.Szumski.myFilms.model;

import com.Szumski.myFilms.model.databaseModels.User;

public class UserInformation {

    private String username;

    public UserInformation(User user) {
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }

}
