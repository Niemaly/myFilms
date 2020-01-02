package com.Szumski.myFilms.pojo.tmdbSearchListspojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllList {
    List<ListElementMovie> results;
    private Integer page;
    private Integer total_results;
    private Dates dates;
    private Integer total_pages;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<ListElementMovie> getResults() {
        return results;
    }

    public void setResults(List<ListElementMovie> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "AllList{" +
                "results=" + results +
                ", page=" + page +
                ", total_results=" + total_results +
                ", dates=" + dates +
                ", total_pages=" + total_pages +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
