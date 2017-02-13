package com.wiryawan.retrofittutorial.service;

import com.wiryawan.retrofittutorial.model.City;
import com.wiryawan.retrofittutorial.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wiryawan on 1/13/17.
 */

public interface ApiInterface {

    @GET("api/jadwal-bioskop")
    Call<City> getCity();

    @GET("api/jadwal-bioskop")
    Call<Movie> getMovie(
            @Query("id") String id);

}
