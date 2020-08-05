package com.example.moviepedia.Db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviepedia.Models.movie

@Dao
interface MoviesDao {

    @Insert
    fun insertMovies(mode:movie)
    @Delete
    fun deleteAllMovies(model:movie)
    @Query("SELECT * FROM movies_tbl")
    fun getAllMovies():LiveData<List<movie>>


}