package com.Szumski.myFilms.model.frontendComunication;

import com.Szumski.myFilms.model.databaseModels.MovieModel;
import com.Szumski.myFilms.model.parsers.FilmParser;
import com.Szumski.myFilms.model.pojo.tmdbSearchListspojo.AllList;

import java.util.ArrayList;
import java.util.List;

public class ResponseAllFilms {

    private List<MovieModel> listOfMovies;
    private Integer numberOfPages;
    private Integer currentPage;
    private Integer numberOfResults;

    public ResponseAllFilms(AllList allList) {
        listOfMovies = new ArrayList<>();

        allList.getResults().stream().forEach(element-> {
            listOfMovies.add(FilmParser.parseToMovieModel(element));
        });

        numberOfPages=allList.getTotal_pages();

        currentPage=allList.getPage();

        numberOfResults=allList.getTotal_results();

    }

    public List<MovieModel> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(List<MovieModel> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(Integer numberOfResults) {
        this.numberOfResults = numberOfResults;
    }
}
