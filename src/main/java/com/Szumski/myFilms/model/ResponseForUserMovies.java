package com.Szumski.myFilms.model;

import java.util.List;

public class ResponseForUserMovies{

    private long id;
    private String comment;
    private String overview;
    private double ratingUser;
    private boolean status;
    private String title;
    private String src;
    private Double rating;
    private String dateOfRelease;
    private List<Integer> genreIdList;

    public ResponseForUserMovies(MovieModel model,UserMovie userMovie) {

        this.id = userMovie.getMovieId();
        this.comment = userMovie.getComment();
        this.ratingUser = userMovie.getRating();
        this.status = userMovie.getStatus();
        this.title = model.getTitle();
        this.src = model.getSrc();
        this.rating = model.getRating();
        this.dateOfRelease = model.getDateOfRelease();
        this.genreIdList = model.getGenreIdList();
        this.overview = model.getOverview();
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
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
