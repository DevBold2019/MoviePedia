package com.example.moviepedia.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Models.movie
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

        Glide.with(context).load(poster).placeholder(R.drawable.loading).into(holder.imageView)
        holder.textView.setText(model.title)




    }


    class viewholder(view:View) : RecyclerView.ViewHolder(view) {


        val imageView:ImageView=view.findViewById(R.id.searchedImage)
        val textView:TextView=view.findViewById(R.id.searchedName)
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

