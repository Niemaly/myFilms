package com.Szumski.myFilms.model;

import java.util.Objects;

public class UserMovie {

    private Long movieId;
    private String comment;
    private double rating;
    boolean status;

    public UserMovie(Long filmId) {
        this.movieId = filmId;
    }

    public UserMovie() {
    }


    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMovie userMovie = (UserMovie) o;
        return movieId == userMovie.movieId &&
                Double.compare(userMovie.rating, rating) == 0 &&
                status == userMovie.status &&
                Objects.equals(comment, userMovie.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, comment, rating, status);
    }
}
