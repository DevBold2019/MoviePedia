package com.example.moviepedia.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Adapters.MovieAdapter
import com.example.moviepedia.Adapters.MyListAdapter
import com.example.moviepedia.Models.MoviesResponse
import com.example.moviepedia.Models.movie

import com.example.moviepedia.R
import com.example.moviepedia.RetroApi.JsonRetroApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var retrofit:Retrofit
    lateinit var adapter: MyListAdapter
    lateinit var key:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        /* Inflate the layout for this fragment */
        val view:View=inflater.inflate(R.layout.fragment_my_list, container, false)

        key="88343a4758ad5bd50971e643e2f2b7de"

        recyclerView=view.findViewById(R.id.myListRecyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager=GridLayoutManager(container!!.context,3)

        retrofit=Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        loadMySavedMovies()






        return view
    }

    fun loadMySavedMovies(){



        val jsonRetroApi: JsonRetroApi =retrofit.create(JsonRetroApi::class.java)
        val call: Call<MoviesResponse> = jsonRetroApi.getPopularMovies(key)

        call.enqueue(object : Callback<MoviesResponse> {


            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (!response.isSuccessful){

                    return

                }




                val list: List<movie>
                list= response.body()!!.results!!

                for (data in list){

                    adapter= MyListAdapter(context!!,list)
                    recyclerView.adapter=adapter
                    adapter.notifyDataSetChanged()


                }

            }
            override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {

                print(t.message.toString())
                Toast.makeText(context!!,""+t.message, Toast.LENGTH_LONG).show()


            }
        })



    }


}
