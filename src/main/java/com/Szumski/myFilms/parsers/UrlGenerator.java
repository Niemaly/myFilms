package com.Szumski.myFilms.parsers;


import com.Szumski.myFilms.pojo.MovieQuery;

public class UrlGenerator {

    private String firstPart ="https://api.themoviedb.org/3/movie/";
    private String apiKey;
    private String language;

    public UrlGenerator(String apiKey, String language) {
        this.apiKey = apiKey;
        this.language = language;
    }

    public String getUpcoming(String page){
        return firstPart+"upcoming?api_key="+apiKey+"&language="+language+"&page="+page;
    }


    public String getNowPlaying(String page){
        return firstPart+"now_playing?api_key="+apiKey+"&language="+language+"&page="+page;
    }


    public String getMovieById(String movieId){
        return firstPart+movieId+"?api_key="+apiKey;
    }

    public String getPopular(String page){
        return firstPart+"popular?api_key="+apiKey+"&language="+language+"&page="+page;
    }


    public String getSimilarMovies(String movieId, String page) {
        return firstPart+movieId+"/similar?api_key="+apiKey+"&language="+language+"&page="+page;
    }

    public String getRecommendations(String movieId, String page) {
        return firstPart+movieId+"/recommendations?api_key="+apiKey+"&language="+language+"&page="+page;
    }

    public String searchMovie(MovieQuery movieQuery) {
        return "https://api.themoviedb.org/3/search/movie?api_key="+apiKey+"&language="
                +language+"&query="+movieQuery.getQuery()+"&page="+movieQuery.getPage()
                +"&include_adult="+movieQuery.getInclude_adult()+"&year="+movieQuery.getYear();
    }
}
