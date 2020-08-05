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
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Models.movie
import com.example.moviepedia.MoreDetailsActivity
import com.example.moviepedia.R


class MovieAdapter(var context:Context,val list: List<movie>) : RecyclerView.Adapter<MovieAdapter.viewholder>() {


    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onItemClick(cardView: CardView, view:View, model: movie, position:Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {

        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        val view:View
        view=LayoutInflater.from(parent.context).inflate(R.layout.popular_movies_layout,parent,false)

        return viewholder(view)


    }

    override fun getItemCount(): Int {


        return  list.size

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val pos=list[position]

        val poster = "https://image.tmdb.org/t/p/w500" + pos.posterPath
        val title:String=pos.title.toString()
        val language:String=pos.originalLanguage.toString()
        val description:String=pos.overview.toString()
        val id:String=pos.id.toString()

        holder.t1.text=pos.title
        holder.t1.isSelected=true
        holder.t3.isSelected=true
        holder.t2.text="Rating : ${pos.voteAverage}"
        var genre:String=""
        var genre1:String=""
        var genre2:String=""
        var genre3:String=""

        for (data in pos.genreIds){

            when(data){

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

            //toast("1:$genre\n2:$genre1\n3:$genre2,\n4:$genre3")
            //toast("1:$genre")

            holder.t3.text=genre

        }


        Glide.with(context).load(poster).placeholder(R.drawable.loading).into(holder.imageView)

        holder.card.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View) {

                val detailIntent: Intent = Intent(context, MoreDetailsActivity::class.java)

                var bundle: Bundle = Bundle()

                bundle.putString("poster",poster)
                bundle.putString("title",title)
                bundle.putString("language",language)
                bundle.putString("description",description)
                bundle.putString("genre",genre)
                bundle.putString("id",id)

                detailIntent.putExtras(bundle)
                context.applicationContext.startActivity(detailIntent)


            }


        })


    }


    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val imageView:ImageView=view.findViewById(R.id.MovieImagePic)

        val t1:TextView=view.findViewById(R.id.TitleMovie)
        val t2:TextView=view.findViewById(R.id.MovieRating)
        val t3:TextView=view.findViewById(R.id.genreTextView)

        val card:CardView=view.findViewById(R.id.movieCard)



    }

    fun toast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

}