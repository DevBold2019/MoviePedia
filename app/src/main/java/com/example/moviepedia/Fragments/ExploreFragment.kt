package com.example.moviepedia.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepedia.Models.MoviesResponse
import com.example.moviepedia.Models.movie

import com.example.moviepedia.R
import com.example.moviepedia.Adapters.SearchAdapter
import com.example.moviepedia.RetroApi.JsonRetroApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class ExploreFragment : Fragment() {

    lateinit var search:SearchView
    lateinit var adapter:SearchAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var textView: TextView
    lateinit var textView1: TextView
    lateinit var key:String
    lateinit var retrofit: Retrofit


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_explore, container, false)

        key="88343a4758ad5bd50971e643e2f2b7de"

        search=view.findViewById(R.id.searchView)
        recyclerView=view.findViewById(R.id.resultsRecyclerView)
        textView=view.findViewById(R.id.resultsText)

        recyclerView.layoutManager= LinearLayoutManager(container!!.context)
        recyclerView.setHasFixedSize(true)



        retrofit=Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                loadFindings(newText)


                return true
            }

        })



        return view
    }



    fun loadFindings(newText: String?){


        val jsonRetroApi: JsonRetroApi =retrofit.create(JsonRetroApi::class.java)
        val call: Call<MoviesResponse> = jsonRetroApi.getSearchedMovies(key,newText)

        call.enqueue(object : Callback<MoviesResponse> {


            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (!response.isSuccessful){

                    textView.setText(""+response.message().toString())


                    return

                }

                textView.text=("\t"+"Results Found")

                val list: MutableList<movie>
                list= (response.body()!!.results as MutableList<movie>?)!!

                for (data in list){

                    textView.text=(list.size.toString()+"\t"+"Results Found")
                    adapter= SearchAdapter(list,context!!)
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
