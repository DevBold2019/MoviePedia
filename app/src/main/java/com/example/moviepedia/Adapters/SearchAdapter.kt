package com.example.moviepedia.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Models.movie
import com.example.moviepedia.MoreDetailsActivity
import com.example.moviepedia.R
import java.util.*




class SearchAdapter(var list: MutableList<movie>,var  context: Context) : RecyclerView.Adapter<SearchAdapter.viewholder> (){


    lateinit var mylist:MutableList<movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

       val view:View=LayoutInflater.from(parent!!.context).inflate(R.layout.search_layout,parent,false)


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

        if(model.genreIds.size < 1){

            genre=""
        }else{

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
        }



        Glide.with(context).load(poster).placeholder(R.drawable.loading).into(holder.imageView)
        holder.textView.setText(model.title)

        holder.cardview.setOnClickListener(object : View.OnClickListener {
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
    class viewholder(view:View) : RecyclerView.ViewHolder(view) {


        val imageView:ImageView=view.findViewById(R.id.searchedImage)
        val textView:TextView=view.findViewById(R.id.searchedName)
        val cardview:CardView=view.findViewById(R.id.searchCard)
    }

    fun getFilter(): Filter {

        return getFilteredData
    }

    var getFilteredData: Filter = object : Filter() {

        override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {

            var myFilteredList:MutableList<movie> = ArrayList()

            if (charSequence == null || charSequence.length == 0) {

                myFilteredList.addAll(mylist)

            } else {

                val getSearchedText = charSequence.toString().toLowerCase().trim { it <= ' ' }

                for (model in list) {

                    if (model.title!!.contains(getSearchedText)) {

                        myFilteredList.add(model)
                        Toast.makeText(context, "Match Found", Toast.LENGTH_LONG).show()

                    } else {

                        Toast.makeText(context, "No Match Found", Toast.LENGTH_LONG).show()

                    }


                }


            }

            val filterResults = Filter.FilterResults()
            filterResults.values = myFilteredList


            return filterResults
        }

        override fun publishResults(
            charSequence: CharSequence,
            filterResults: Filter.FilterResults
        ) {

            list.clear()
            list.addAll(filterResults.values as Collection<movie>)
            notifyDataSetChanged()


        }
    }

}

