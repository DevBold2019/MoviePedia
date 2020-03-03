package com.example.moviepedia.Fragments


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Models.MoviesResponse
import com.example.moviepedia.Models.movie

import com.example.moviepedia.R
import com.example.moviepedia.Recyclers.MovieAdapter
import com.example.moviepedia.Recyclers.UpcomingAdapter
import com.example.moviepedia.RetroApi.JsonRetroApi
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var retrofit: Retrofit
    lateinit var adapter: MovieAdapter
    lateinit var adapter1: MovieAdapter
    lateinit var receycler: RecyclerView
    lateinit var receycler1: RecyclerView
    lateinit var receycler2: RecyclerView
    lateinit var receycler3: RecyclerView
    lateinit var key:String
    lateinit var bottom: BottomSheetDialog
    lateinit var dialog: Dialog
    lateinit var textView: TextView
    lateinit var textView1: TextView
    lateinit var textView2: TextView
    lateinit var scrollView: ScrollView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_home, container, false)

        key="88343a4758ad5bd50971e643e2f2b7de"



        dialog= Dialog(container!!.context)
        dialog.setContentView(R.layout.more_details_layout)

        // dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent);

        receycler=view.findViewById(R.id.popularRecycler)
        receycler1=view.findViewById(R.id.TopRatedRecycler)
        receycler2=view.findViewById(R.id.UpcomingRecyclerView)
        receycler3=view.findViewById(R.id.nowPlayingRecyclerView)
        textView=view.findViewById( R.id.see)
        textView1=view.findViewById( R.id.see1)

        receycler.layoutManager= LinearLayoutManager(container.context, LinearLayoutManager.HORIZONTAL,false)
        receycler.setHasFixedSize(true)

        receycler1.layoutManager= LinearLayoutManager(container.context, LinearLayoutManager.HORIZONTAL,false)
        receycler1.setHasFixedSize(true)

        receycler2.layoutManager= LinearLayoutManager(container.context, LinearLayoutManager.HORIZONTAL,false)
        receycler2.setHasFixedSize(true)


        receycler3.layoutManager= LinearLayoutManager(container.context, LinearLayoutManager.HORIZONTAL,false)
        receycler3.setHasFixedSize(true)


        retrofit=Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        loadPopularMovies()
        loadTopRated()
        UpcomingMovies()
        nowPlaying()


        return view


    }

    fun loadPopularMovies(){



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

                    adapter= MovieAdapter(context!!,list)
                    receycler.adapter=adapter
                    adapter.notifyDataSetChanged()

                    textView.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(p0: View?) {


                            receycler.scrollToPosition(list.size-1)

                        }


                    })


                    adapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {

                        override fun onItemClick(cardView: CardView, view: View, model: movie, position: Int) {

                            Toast.makeText(context!!,""+model.id, Toast.LENGTH_LONG).show()

                            val poster = "https://image.tmdb.org/t/p/w500" + model.posterPath


                            //Showing Related Movies
                            /*   val jsonRetroApi:JsonRetroApi=retrofit.create(JsonRetroApi::class.java)
                               val call: Call<MoviesResponse> = jsonRetroApi.getSimilarMovies(model.id!!.toInt(),key)



                               call.enqueue(object : Callback<MoviesResponse> {


                                   override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                                       if (!response.isSuccessful){

                                           return

                                       }

                                       scrollView.visibility=View.VISIBLE
                                       linearLayout1.visibility=View.GONE



                                       val list: List<movie>
                                       list= response.body()!!.results!!

                                       for (data in list){

                                          Toast.makeText(applicationContext,"title\t"+data.title,Toast.LENGTH_LONG).show()


                                       }

                                   }
                                   override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {

                                       print(t.message.toString())

                                       scrollView.visibility=View.GONE
                                       linearLayout1.visibility=View.VISIBLE

                                   }
                               })
   */

                            dialog.show()

                            val t1:TextView=dialog.findViewById(R.id.Titles)
                            t1.text=model.title.toString()

                            val t2:TextView=dialog.findViewById(R.id.Description)
                            t2.text=model.overview

                            /* val t3:TextView=dialog.findViewById(R.id.Time)
                             t3.text=model.*/

                            val t4:TextView=dialog.findViewById(R.id.Language)
                            t4.text=model.originalLanguage



                            val imageView: ImageView =dialog.findViewById(R.id.DetailPic)
                            Glide.with(context!!).load(poster).placeholder(R.drawable.loading).into(imageView)

                            val imageView1: ImageView =dialog.findViewById(R.id.closeImage)

                            imageView1.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {

                                    dialog.dismiss()
                                }


                            })


                            val imageView2: ImageView =dialog.findViewById(R.id.unliked)
                            val imageView3: ImageView =dialog.findViewById(R.id.liked)

                            imageView2.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {



                                    imageView3.visibility=View.VISIBLE
                                    Toast.makeText(context!!,"Added to liked", Toast.LENGTH_LONG).show()


                                    imageView2.visibility=View.INVISIBLE

                                }


                            })


                            imageView3.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {



                                    imageView2.visibility=View.VISIBLE
                                    Toast.makeText(context!!,"Removed from Likes", Toast.LENGTH_LONG).show()

                                    imageView3.visibility=View.INVISIBLE

                                }


                            })

                        }


                    })

                }

            }
            override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {

                print(t.message.toString())
                Toast.makeText(context!!,""+t.message, Toast.LENGTH_LONG).show()


            }
        })



    }


    fun loadTopRated(){


        val jsonRetroApi: JsonRetroApi =retrofit.create(JsonRetroApi::class.java)

        val call: Call<MoviesResponse> = jsonRetroApi.getTopRatedMovies(key)

        call.enqueue(object : Callback<MoviesResponse> {

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (! response.isSuccessful){


                    return

                }



                val list: List<movie>
                list= response.body()!!.results!!

                for (data in list){

                    adapter1= MovieAdapter(context!!,list)
                    receycler1.adapter=adapter1
                    adapter1.notifyDataSetChanged()

                    textView1.setOnClickListener(object : View.OnClickListener {

                        override fun onClick(p0: View?) {

                            receycler1.scrollToPosition(list.size-1)


                        }

                    })




                    adapter1.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {

                        override fun onItemClick(cardView: CardView, view: View, model: movie, position: Int) {


                            // Toast.makeText(applicationContext,""+model.video,Toast.LENGTH_LONG).show()

                            val poster = "https://image.tmdb.org/t/p/w500" + model.posterPath


                            dialog.show()

                            val t1:TextView=dialog.findViewById(R.id.Titles)
                            t1.text=model.title.toString()

                            val t2:TextView=dialog.findViewById(R.id.Description)
                            t2.text=model.overview

                            /* val t3:TextView=dialog.findViewById(R.id.Time)
                             t3.text=model.*/

                            val t4:TextView=dialog.findViewById(R.id.Language)
                            t4.text=model.originalLanguage



                            val imageView: ImageView =dialog.findViewById(R.id.DetailPic)
                            Glide.with(context!!).load(poster).placeholder(R.drawable.loading).into(imageView)

                            val imageView1: ImageView =dialog.findViewById(R.id.closeImage)

                            imageView1.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {

                                    dialog.dismiss()
                                }


                            })


                            val imageView2: ImageView =dialog.findViewById(R.id.unliked)
                            val imageView3: ImageView =dialog.findViewById(R.id.liked)

                            imageView2.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {



                                    imageView3.visibility=View.VISIBLE
                                    Toast.makeText(context!!,"Added to liked", Toast.LENGTH_LONG).show()


                                    imageView2.visibility=View.INVISIBLE

                                }


                            })


                            imageView3.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {



                                    imageView2.visibility=View.VISIBLE
                                    Toast.makeText(context!!,"Removed from Likes", Toast.LENGTH_LONG).show()

                                    imageView3.visibility=View.INVISIBLE

                                }


                            })

                        }


                    })

                }

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

                println(t.message)
                Toast.makeText(context!!,""+t.message, Toast.LENGTH_LONG).show()

            }


        })





    }

    fun UpcomingMovies(){



        val jsonRetroApi: JsonRetroApi =retrofit.create(JsonRetroApi::class.java)
        val call: Call<MoviesResponse> = jsonRetroApi.getUpcomingMovies(key)

        call.enqueue(object : Callback<MoviesResponse> {


            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (!response.isSuccessful){

                    return

                }




                val list: List<movie>
                list= response.body()!!.results!!

                for (data in list){

                    val upcomingAdapter = UpcomingAdapter(context!!,list)
                    receycler2.adapter=upcomingAdapter
                    upcomingAdapter.notifyDataSetChanged()



                }

            }
            override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {

                print(t.message.toString())
                Toast.makeText(context!!,""+t.message, Toast.LENGTH_LONG).show()


            }
        })







    }

    fun nowPlaying(){

        val jsonRetroApi: JsonRetroApi =retrofit.create(JsonRetroApi::class.java)
        val call: Call<MoviesResponse> = jsonRetroApi.getNowPlayingMovies(key)

        call.enqueue(object : Callback<MoviesResponse> {


            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (!response.isSuccessful){

                    return

                }

                val list: List<movie>
                list= response.body()!!.results!!

                for (data in list){

                    adapter= MovieAdapter(context!!,list)
                    receycler3.adapter=adapter
                    adapter.notifyDataSetChanged()



                    adapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {

                        override fun onItemClick(cardView: CardView, view: View, model: movie, position: Int) {

                            Toast.makeText(context!!,""+model.id, Toast.LENGTH_LONG).show()

                            val poster = "https://image.tmdb.org/t/p/w500" + model.posterPath


                            //Showing Related Movies
                            /*   val jsonRetroApi:JsonRetroApi=retrofit.create(JsonRetroApi::class.java)
                               val call: Call<MoviesResponse> = jsonRetroApi.getSimilarMovies(model.id!!.toInt(),key)



                               call.enqueue(object : Callback<MoviesResponse> {


                                   override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                                       if (!response.isSuccessful){

                                           return

                                       }

                                       scrollView.visibility=View.VISIBLE
                                       linearLayout1.visibility=View.GONE



                                       val list: List<movie>
                                       list= response.body()!!.results!!

                                       for (data in list){

                                          Toast.makeText(applicationContext,"title\t"+data.title,Toast.LENGTH_LONG).show()


                                       }

                                   }
                                   override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {

                                       print(t.message.toString())

                                       scrollView.visibility=View.GONE
                                       linearLayout1.visibility=View.VISIBLE

                                   }
                               })
   */

                            dialog.show()

                            val t1:TextView=dialog.findViewById(R.id.Titles)
                            t1.text=model.title.toString()

                            val t2:TextView=dialog.findViewById(R.id.Description)
                            t2.text=model.overview

                            /* val t3:TextView=dialog.findViewById(R.id.Time)
                             t3.text=model.*/

                            val t4:TextView=dialog.findViewById(R.id.Language)
                            t4.text=model.originalLanguage



                            val imageView: ImageView =dialog.findViewById(R.id.DetailPic)
                            Glide.with(context!!).load(poster).placeholder(R.drawable.loading).into(imageView)

                            val imageView1: ImageView =dialog.findViewById(R.id.closeImage)

                            imageView1.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {

                                    dialog.dismiss()
                                }


                            })


                            val imageView2: ImageView =dialog.findViewById(R.id.unliked)
                            val imageView3: ImageView =dialog.findViewById(R.id.liked)

                            imageView2.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {



                                    imageView3.visibility=View.VISIBLE
                                    Toast.makeText(context!!,"Added to liked", Toast.LENGTH_LONG).show()


                                    imageView2.visibility=View.INVISIBLE

                                }


                            })


                            imageView3.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {



                                    imageView2.visibility=View.VISIBLE
                                    Toast.makeText(context!!,"Removed from Likes", Toast.LENGTH_LONG).show()

                                    imageView3.visibility=View.INVISIBLE

                                }


                            })

                        }


                    })

                }

            }
            override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {

                print(t.message.toString())
                Toast.makeText(context!!,""+t.message, Toast.LENGTH_LONG).show()


            }
        })


    }



}
