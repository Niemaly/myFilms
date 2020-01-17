package com.Szumski.myFilms.model.databaseModels;

import com.Szumski.myFilms.exceptions.MovieIsNotExistInDatabaseException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "userMovies")
public class UserMoviesList {
    @Id
    Long id;
    List <UserMovie> listOfMovies;

    public UserMoviesList() {
    }

    public UserMoviesList(List<UserMovie> listOfMovies, Long id) {
        this.listOfMovies = listOfMovies;
    }

    public void addFilmToList(UserMovie userFilm){

        if(!listOfMovies.contains(userFilm)) {
            listOfMovies.add(userFilm);
        }
    }

    public void deleteFilmFromList(Long movieId){
        int index=-1;
        for (int i = 0; i <listOfMovies.size() ; i++) {
            if (movieId==listOfMovies.get(i).getId())
                index=i;
        }
        if (index!=-1) {
            listOfMovies.remove(index);
        } else {
            throw new MovieIsNotExistInDatabaseException();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMoviesList that = (UserMoviesList) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(listOfMovies, that.listOfMovies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listOfMovies);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserMovie> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(List<UserMovie> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    public void changeMovieStatus(Long movieId) {
        int index=-1;
        for (int i = 0; i <listOfMovies.size() ; i++) {
            if (movieId==listOfMovies.get(i).getId())
                index=i;
        }

        if (index!=-1){
            UserMovie userMovie = listOfMovies.get(index);
            userMovie.setStatus(!userMovie.getStatus());
        } else{
            System.out.println("ERROR");
            throw new MovieIsNotExistInDatabaseException();
        }

    }
}
