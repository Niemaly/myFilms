package com.Szumski.myFilms.service;

import com.Szumski.myFilms.model.databaseModels.AutoincrementId;
import com.Szumski.myFilms.model.databaseModels.User;
import com.Szumski.myFilms.model.databaseModels.UserMovie;

import com.Szumski.myFilms.model.databaseModels.UserMoviesList;
import com.Szumski.myFilms.repository.UserMoviesListRepository;
import com.Szumski.myFilms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserMoviesService {

    private UserMoviesListRepository userMoviesListRepository;
    private UserRepository userRepository;
    private User user;

    @Autowired
    public UserMoviesService(UserMoviesListRepository userMoviesListRepository, UserRepository userRepository) {
        this.userMoviesListRepository = userMoviesListRepository;
        this.userRepository = userRepository;
    }

    public UserMoviesService(User user) {
        this.user = user;
    }

    public UserMoviesService() {
    }


    public void createNewUser(User user){

        AutoincrementId autoincrementId = new AutoincrementId(userRepository);

        UserMoviesList userMoviesList = new UserMoviesList();
        userMoviesList.setId(user.getIdFilmList());
        userMoviesList.setListOfMovies(new ArrayList<>());

        userMoviesList.setId(autoincrementId.createIdOfUserMoviesList());

        userMoviesListRepository.save(userMoviesList);
        user.setIdFilmList(userMoviesList.getId());
        userRepository.save(user);

    }

    public void saveMovieToUser(UserMovie userMovie, User user){

            UserMoviesList userMoviesList = userMoviesListRepository.findById(user.getIdFilmList()).get();

            userMoviesList.addFilmToList(userMovie);
            userMoviesListRepository.save(userMoviesList);
            System.out.println("movie saved : "+ userMovie.toString());

    }

    public boolean checkIsMovieExistInUser(User user, Long id){

        return userMoviesListRepository.findById(user.getIdFilmList()).get().isMovieInList(id);


    }

    public void deleteMovieFromUser(User user ,Long id){
        if (checkIsMovieExistInUser(user, id)) {
            UserMoviesList userMoviesList = userMoviesListRepository.findById(user.getIdFilmList()).get();
            userMoviesList.deleteFilmFromList(id);
            userMoviesListRepository.save(userMoviesList);
        }else{
            System.out.println("aaaaaaaaaaaaaaaaaaaaa");
        }

    }

    public void changeMovieStatus(Long id){
        UserMoviesList userMoviesList = userMoviesListRepository.findById(user.getIdFilmList()).get();
        userMoviesList.changeMovieStatus(id);
        userMoviesListRepository.save(userMoviesList);
    }


    public List<UserMovie> getAllMovies(Long idOfUserFilmList) {
        return  userMoviesListRepository.findById(idOfUserFilmList).get().getListOfMovies();
    }

    public UserMoviesListRepository getUserMoviesListRepository() {
        return userMoviesListRepository;
    }

    public void setUserMoviesListRepository(UserMoviesListRepository userMoviesListRepository) {
        this.userMoviesListRepository = userMoviesListRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
