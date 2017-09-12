package com.erikstackelberg.upcoming.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviePeopleResponse {
    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private ArrayList<MoviePerson> moviePeople;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<MoviePerson> getMoviePeople() {
        return moviePeople;
    }

    public void setMoviePeople(ArrayList<MoviePerson> moviePeople) {
        this.moviePeople = moviePeople;
    }
}
