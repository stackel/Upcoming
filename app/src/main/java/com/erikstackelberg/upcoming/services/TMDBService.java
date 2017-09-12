package com.erikstackelberg.upcoming.services;

import com.erikstackelberg.upcoming.model.MoviePeopleResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBService {
    String API_BASE_URL = "https://api.themoviedb.org/3/";
    String API_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
    String API_KEY = "a9c6a013fa7c8410ff0c7959ad9bdb07";

    @GET("search/person?api_key=" + API_KEY)
    Observable<MoviePeopleResponse> searchPeople(@Query("query") String query);
}
