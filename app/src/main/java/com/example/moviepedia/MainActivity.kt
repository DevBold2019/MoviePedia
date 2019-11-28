package com.example.moviepedia

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Recyclers.MovieAdapter
import com.example.moviepedia.RetroApi.JsonRetroApi
import com.example.moviepedia.RetroApi.MoviesResponse
import com.example.moviepedia.RetroApi.movie
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var adapter:MovieAdapter
    lateinit var adapter1:MovieAdapter
    lateinit var receycler: RecyclerView
    lateinit var receycler1: RecyclerView
    lateinit var key:String
    lateinit var bottom: BottomSheetDialog
    lateinit var dialog:Dialog
    lateinit var textView:TextView
    lateinit var textView1:TextView
    lateinit var textView2:TextView
    lateinit var linearLayout1: LinearLayout
    lateinit var scrollView: ScrollView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        key="88343a4758ad5bd50971e643e2f2b7de"



        dialog= Dialog(this@MainActivity)
        dialog.setContentView(R.layout.more_details_layout)

       // dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent);

        receycler=findViewById(R.id.popularRecycler)
        receycler1=findViewById(R.id.TopRatedRecycler)

        textView=findViewById( R.id.see)
        textView1=findViewById( R.id.see1)
        textView2=findViewById( R.id.errorDetail)

       scrollView =findViewById(R.id.contentPage)
        linearLayout1=findViewById(R.id.errorpage)


        receycler.layoutManager= LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        receycler.setHasFixedSize(true)

        receycler1.layoutManager= LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        receycler1.setHasFixedSize(true)


        retrofit=Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        loadPopularMovies()
        loadTopRated()

    }


    fun loadPopularMovies(){


        val jsonRetroApi:JsonRetroApi=retrofit.create(JsonRetroApi::class.java)

        val call: Call<MoviesResponse> = jsonRetroApi.getPopularMovies(key)

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

                    adapter= MovieAdapter(applicationContext,list)
                    receycler.adapter=adapter
                    adapter.notifyDataSetChanged()

                    textView.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(p0: View?) {


                            receycler.scrollToPosition(list.size-1)

                        }


                    })


                    adapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {

                        override fun onItemClick(cardView: CardView, view: View, model: movie, position: Int) {

                            Toast.makeText(applicationContext,""+model.video,Toast.LENGTH_LONG).show()

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



                            val imageView:ImageView=dialog.findViewById(R.id.DetailPic)
                            Glide.with(applicationContext).load(poster).placeholder(R.drawable.loading).into(imageView)

                            val imageView1:ImageView=dialog.findViewById(R.id.closeImage)

                            imageView1.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {

                                    dialog.dismiss()
                                }


                            })


                            val imageView2:ImageView=dialog.findViewById(R.id.unliked)
                            val imageView3:ImageView=dialog.findViewById(R.id.liked)

                            imageView2.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {



                                    imageView3.visibility=View.VISIBLE
                                    Toast.makeText(applicationContext,"Added to liked",Toast.LENGTH_LONG).show()


                                    imageView2.visibility=View.INVISIBLE

                                }


                            })


                            imageView3.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {



                                    imageView2.visibility=View.VISIBLE
                                    Toast.makeText(applicationContext,"Removed from Likes",Toast.LENGTH_LONG).show()

                                    imageView3.visibility=View.INVISIBLE

                                }


                            })

                        }


                    })

                }

            }
            override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {

                print(t.message.toString())

                scrollView.visibility=View.GONE
                linearLayout1.visibility=View.VISIBLE

            }
        })



    }


    fun loadTopRated(){


        val jsonRetroApi:JsonRetroApi=retrofit.create(JsonRetroApi::class.java)

        val call: Call<MoviesResponse> = jsonRetroApi.getTopRatedMovies(key)

        call.enqueue(object : Callback<MoviesResponse> {

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

                if (! response.isSuccessful){


                    return

                }

                scrollView.visibility=View.VISIBLE
                linearLayout1.visibility=View.GONE


                val list: List<movie>
                list= response.body()!!.results!!

                for (data in list){

                    adapter1= MovieAdapter(applicationContext,list)
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



                            val imageView:ImageView=dialog.findViewById(R.id.DetailPic)
                            Glide.with(applicationContext).load(poster).placeholder(R.drawable.loading).into(imageView)

                            val imageView1:ImageView=dialog.findViewById(R.id.closeImage)

                            imageView1.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {

                                    dialog.dismiss()
                                }


                            })


                            val imageView2:ImageView=dialog.findViewById(R.id.unliked)
                            val imageView3:ImageView=dialog.findViewById(R.id.liked)

                            imageView2.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {

                                    

                                        imageView3.visibility=View.VISIBLE
                                        Toast.makeText(applicationContext,"Added to liked",Toast.LENGTH_LONG).show()


                                    imageView2.visibility=View.INVISIBLE

                                }


                            })


                            imageView3.setOnClickListener(object : View.OnClickListener {

                                override fun onClick(p0: View?) {



                                    imageView2.visibility=View.VISIBLE
                                    Toast.makeText(applicationContext,"Removed from Likes",Toast.LENGTH_LONG).show()

                                    imageView3.visibility=View.INVISIBLE

                                }


                            })

                        }


                    })

                }

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

                 println(t.message)

                scrollView.visibility=View.GONE
                linearLayout1.visibility=View.VISIBLE

                textView2.text="Can't Reach For Movies"+"\n make sure you're connected to the internet"

               // Toast.makeText(applicationContext,"Error Loading Movies\t Please ensure you're connected to the internet",Toast.LENGTH_LONG).show()



            }


        })





    }


    override fun onStart() {
        super.onStart()

        loadTopRated()
       loadPopularMovies()
    }

    override fun onRestart() {
        super.onRestart()

        loadTopRated()
        loadPopularMovies()

    }

    override fun onResume() {
        super.onResume()

        loadTopRated()
        loadPopularMovies()
    }



}
