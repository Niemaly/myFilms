package com.Szumski.myFilms.model.frontendComunication;

public class MovieQuery {
    private Integer page;
    private Boolean include_adult;
    private String region;
    private String year;
    private String primary_release_year;
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getInclude_adult() {
        return include_adult;
    }

    public void setInclude_adult(Boolean include_adult) {
        this.include_adult = include_adult;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrimary_release_year() {
        return primary_release_year;
    }

    public void setPrimary_release_year(String primary_release_year) {
        this.primary_release_year = primary_release_year;
    }
}
