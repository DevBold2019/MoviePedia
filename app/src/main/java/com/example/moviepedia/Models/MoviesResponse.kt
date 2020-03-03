package com.example.moviepedia.Models


import com.example.moviepedia.Models.movie
import com.google.gson.annotations.SerializedName

class MoviesResponse {

    @SerializedName("page")
     val page = 0
    @SerializedName("results")
    val results: List<movie>?=null
    @SerializedName("total_results")
     val totalResults = 0
    @SerializedName("total_pages")
   val totalPages = 0


}
