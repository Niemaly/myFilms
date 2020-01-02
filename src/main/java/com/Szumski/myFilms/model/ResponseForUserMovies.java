package com.Szumski.myFilms.model;

import java.util.List;

public class ResponseForUserMovies{

    private long filmId;
    private String comment;
    private double ratingUser;
    boolean status;
    String title;
    String src;
    Double ratingDb;
    String dateOfRelease;
    List<Integer> genreIdList;

    public ResponseForUserMovies(MovieModel model,UserMovie userMovie) {

        this.filmId = userMovie.getMovieId();
        this.comment = userMovie.getComment();
        this.ratingUser = userMovie.getRating();
        this.status = userMovie.getStatus();
        this.title = model.getTitle();
        this.src = model.getSrc();
        this.ratingDb = model.getRating();
        this.dateOfRelease = model.getDateOfRelease();
        this.genreIdList = model.getGenreIdList();
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRatingUser() {
        return ratingUser;
    }

    public void setRatingUser(double ratingUser) {
        this.ratingUser = ratingUser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Double getRatingDb() {
        return ratingDb;
    }

    public void setRatingDb(Double ratingDb) {
        this.ratingDb = ratingDb;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public List<Integer> getGenreIdList() {
        return genreIdList;
    }

    public void setGenreIdList(List<Integer> genreIdList) {
        this.genreIdList = genreIdList;
    }
}
