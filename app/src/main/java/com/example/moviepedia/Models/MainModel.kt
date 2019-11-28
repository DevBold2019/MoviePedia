package com.example.moviepedia.Models

import com.google.gson.annotations.SerializedName

class MainModel(
@SerializedName("Title")
    val title:String,
@SerializedName("Year")
    val year:String,
@SerializedName("Rated")
    val Rated:String,
@SerializedName("Released")
    val Release:String,
@SerializedName("Runtime")
    val Runtime:String,
@SerializedName("Genre")
    val Genre:String,
@SerializedName("Director")
    val Director:String,
@SerializedName("Writer")
    val Writer:String,
@SerializedName("Actors")
    val Actors:String,
@SerializedName("Plot")
    val Plot:String,
@SerializedName("Language")
    val Language:String,
@SerializedName("Country")
    val Country:String,
@SerializedName("Awards")
    val Awards:String,
@SerializedName("Poster")
    val posters:String,
@SerializedName("Ratings")
val list:List<RatingModel>,

@SerializedName("Metascore")
val score:String,
@SerializedName("imdbRating")
val rating:String,
@SerializedName("imdbVotes")
val votes:String,
@SerializedName("imdbID")
val imId:String,
@SerializedName("Type")
val type:String,
@SerializedName("DVD")
val dvd:String,
@SerializedName("BoxOffice")
val office:String,
@SerializedName("Production")
val production:String,
@SerializedName("Website")
val web:String,
@SerializedName("Response")
val response:String







) {
}