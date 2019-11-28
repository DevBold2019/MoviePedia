package com.example.moviepedia.Recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.R
import com.example.moviepedia.RetroApi.movie


class MovieAdapter(var context:Context,val list: List<movie>) : RecyclerView.Adapter<MovieAdapter.viewholder>() {


    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onItemClick(cardView: CardView,  view:View,  model:movie,  position:Int)
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

        holder.t1.text=pos.title
        holder.t2.text=pos.voteAverage.toString()


        Glide.with(context).load(poster).placeholder(R.drawable.loading).into(holder.imageView)

        holder.card.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View) {

                if (mListener !=null){

                    mListener!!.onItemClick(holder.card,p0,pos,position)


                }


            }


        })


    }


    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val imageView:ImageView=view.findViewById(R.id.MovieImagePic)

        val t1:TextView=view.findViewById(R.id.TitleMovie)
        val t2:TextView=view.findViewById(R.id.MovieRating)

        val card:CardView=view.findViewById(R.id.movieCard)



    }

}