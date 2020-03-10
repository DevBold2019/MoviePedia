package com.example.moviepedia.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Models.MyListModel
import com.example.moviepedia.Models.movie
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

        Glide.with(context).load(poster).placeholder(R.drawable.loading).into(holder.imageView)





    }




    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val imageView:ImageView=view.findViewById(R.id.myListImage)


    }

}