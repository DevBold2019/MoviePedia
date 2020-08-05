package com.example.moviepedia

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.moviepedia.Db.MovieDatabase
import com.example.moviepedia.Db.MoviesDao
import com.example.moviepedia.Models.movie

class MovieRepository(application: Application) {

    lateinit var dao: MoviesDao
    lateinit var allMovies:LiveData<List<movie>>
    lateinit var database: MovieDatabase

    init {
       database= MovieDatabase.getInstance(application)
        dao=database.dao()
        allMovies=dao.getAllMovies()


    }


    fun insertMovies(model:movie){

        newMoviesAsync(dao).execute(model)
    }

    private class newMoviesAsync(dao: MoviesDao) :AsyncTask<movie,Unit,Unit>() {

        val mydao=dao
        override fun doInBackground(vararg params: movie?) {

            mydao.insertMovies(params[0]!!)


        }

    }

    fun listMovies() :LiveData<List<movie>>{

        return allMovies
    }


}