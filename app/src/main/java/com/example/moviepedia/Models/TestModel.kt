package com.example.moviepedia.Models

import com.google.gson.annotations.SerializedName

class TestModel(

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
    val list:List<RatingModel>






) {
}