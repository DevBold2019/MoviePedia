package com.example.moviepedia.RetroApi

import com.example.moviepedia.Models.MoviesResponse
import com.example.moviepedia.Models.TestModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonRetroApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>

    @GET("{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id")movie_id:Int, @Query("api_key")apiKey: String?): Call<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>


    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String?): Call<MoviesResponse>

    //search/movie?api_key=88343a4758ad5bd50971e643e2f2b7de&query=spiderman
    @GET("multi")
    fun getSearchedMovies(@Query("api_key") apiKey: String?, @Query("query")query:String?): Call<MoviesResponse>



}