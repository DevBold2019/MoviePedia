package com.example.moviepedia.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Models.MyListModel
import com.example.moviepedia.Models.movie
import com.example.moviepedia.MoreDetailsActivity
import com.example.moviepedia.R

class MyListAdapter(var context: Context,var list: List<movie>) : RecyclerView.Adapter<MyListAdapter.viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
      val view:View=LayoutInflater.from(context).inflate(R.layout.my_list_layout,parent,false)



        return viewholder(view)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val model=list[position]

        val poster = "https://image.tmdb.org/t/p/w500" + model.posterPath

        val title:String=model.title.toString()
        val language:String=model.originalLanguage.toString()
        val description:String=model.overview.toString()
        val id:String=model.id.toString()

        var genre:String=""
        when(model.genreIds[0]){

            28 ->{
                genre= "Action"
            }
            12 ->{
                genre= "Action"
            }
            53 ->{
                genre= "Thriller"
            }

            16 -> {

                genre= "Animation"
            }

            35 -> {

                genre="Comedy"
            }

            80 -> {

                genre = "Crime"
            }

            99 -> {
                genre= "Documentary"
            }

            18 -> {

                genre= "Drama"
            }

            10751 -> {

                genre= "Family"
            }
            14 -> {

                genre= "Fantasy"
            }
            36 -> {

                genre= "History"
            }
            27 -> {

                genre= "Horror"
            }
            10402 -> {

                genre= "Music"
            }
            9648 -> {

                genre= "Mystery"
            }
            10749 -> {

                genre= "Romance"
            }
            878 -> {

                genre= "Fiction"
            }
            10770 -> {

                genre= "Movie"
            }
            10752 -> {

                genre= "War"
            }
            37 -> {

                genre= "Western"
            }

        }


        Glide.with(context).load(poster).placeholder(R.drawable.loading).into(holder.imageView)


        holder.imageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val detailIntent: Intent = Intent(context, MoreDetailsActivity::class.java)

                var bundle: Bundle = Bundle()

                bundle.putString("poster",poster)
                bundle.putString("title",title)
                bundle.putString("language",language)
                bundle.putString("description",description)
                bundle.putString("id",id)
                bundle.putString("genre",genre)

                detailIntent.putExtras(bundle)
                context.applicationContext.startActivity(detailIntent)



            }


        })





    }




    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val imageView:ImageView=view.findViewById(R.id.myListImage)


    }

}