package com.example.moviepedia.Db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class MovieDatabase(): RoomDatabase() {

    abstract fun dao():MoviesDao

    companion object {

        val instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {

            if (instance == null) {

                synchronized(MovieDatabase::class) {

                    Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movieDBA"
                    ).fallbackToDestructiveMigration().build()


                }


            }

            return instance!!
        }


    }










}