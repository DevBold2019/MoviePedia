package com.example.moviepedia.RetroApi

import com.example.moviepedia.Models.MoviesResponse
import com.example.moviepedia.Models.TestModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonRetroApi {

    @GET("14tptq")

    fun getMovies() :Call<List<TestModel>>

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>

    @GET("movie/{id}/similar")
    fun getSimilarMovies(@Path("id")movie_id:Int, @Query("api_key") apiKey: String?): Call<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>


    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>

    @GET("search/movie")
    fun getSearchedMovies(@Query("api_key") apiKey: String?,@Query("query")query:String?): Call<MoviesResponse>



}