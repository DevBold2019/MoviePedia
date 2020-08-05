package com.example.moviepedia

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.moviepedia.Db.MovieDatabase
import com.example.moviepedia.Models.movie

class movieViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var repository: MovieRepository
    lateinit var allMovies:LiveData<List<movie>>

    init {

        repository= MovieRepository(application)
        allMovies=repository.listMovies()


    }

    fun insertMovies(model:movie){

        repository.insertMovies(model)

    }
    fun listMovies() :LiveData<List<movie>>{

        return allMovies
    }



}