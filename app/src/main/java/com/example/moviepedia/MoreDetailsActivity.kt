package com.example.moviepedia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Adapters.MovieAdapter
import com.example.moviepedia.Adapters.MyListAdapter
import com.example.moviepedia.Fragments.HomeFragment
import com.example.moviepedia.Models.MoviesResponse
import com.example.moviepedia.Models.movie
import com.example.moviepedia.RetroApi.JsonRetroApi
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MoreDetailsActivity : AppCompatActivity() {

    lateinit var myexoplayerView:SimpleExoPlayerView
    lateinit var  exoplayer: SimpleExoPlayer
    lateinit var bandWithMeter:BandwidthMeter
    lateinit var trackSelectopr:TrackSelector
    lateinit var    videoUri:Uri
    lateinit var httpFactoryDefault:DefaultHttpDataSourceFactory
    lateinit var extractorsFactory: ExtractorsFactory
    lateinit var mediaSource: MediaSource
    val urlForVideo:String="https://www.radiantmediaplayer.com/media/bbb-360p.mp4"
    lateinit var retrofit:Retrofit
    //val urlForVideo:String="https://www.youtube.com/watch?v=K_tLp7T6U1c"

    lateinit var toolbar: Toolbar
    lateinit var posterImage:ImageView
    lateinit var descriptionTextView:TextView
    lateinit var  languageTextView: TextView
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:MyListAdapter
    lateinit var genreTextView: TextView
     var id:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_details)

        toolbar=findViewById(R.id.moredetailToolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("")

        posterImage=findViewById(R.id.posterImage)
        descriptionTextView=findViewById(R.id.Description)
        languageTextView=findViewById(R.id.Language)
        genreTextView=findViewById(R.id.genreTextView)

        recyclerView=findViewById(R.id.similarMoviesRecyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager=GridLayoutManager(applicationContext,3)


        val poster=getIntent().extras!!.getString("poster")
        val description=getIntent().extras!!.getString("description")
        val language=getIntent().extras!!.getString("language")
        val title=getIntent().extras!!.getString("title")
        val genre=getIntent().extras!!.getString("genre")
         id=getIntent().extras!!.getString("id")!!.toInt()




        val titleTextView:TextView=findViewById(R.id.nameOfTheMovie)

        getSimilarMovies(id)

        Glide.with(applicationContext).load(poster.toString()).into(posterImage)
        descriptionTextView.text=description.toString()
        if (language.toString().equals("en")){

            languageTextView.text="English"
        }else{
            languageTextView.text=language.toString()
        }
        titleTextView.text=title.toString()
        genreTextView.text=genre

       /* myexoplayerView=findViewById(R.id.myExoplayer)

        try {

            bandWithMeter=DefaultBandwidthMeter()
            trackSelectopr=DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandWithMeter))
            exoplayer=ExoPlayerFactory.newSimpleInstance(applicationContext,trackSelectopr)
            videoUri=Uri.parse(urlForVideo)
            httpFactoryDefault= DefaultHttpDataSourceFactory("exoplayer_video")
            extractorsFactory=DefaultExtractorsFactory()
            mediaSource=ExtractorMediaSource(videoUri,httpFactoryDefault,extractorsFactory,null,null)

            myexoplayerView.player=exoplayer
            exoplayer.prepare(mediaSource)
            exoplayer.playWhenReady=true

        }catch (e: IOException){

            print(e.toString())

        }*/


    }


    fun getSimilarMovies(movieid:Int){

        retrofit= Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/movie/" )
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val jsonRetroApi: JsonRetroApi =retrofit.create(JsonRetroApi::class.java)
        val call: Call<MoviesResponse> = jsonRetroApi.getSimilarMovies(movieid,"88343a4758ad5bd50971e643e2f2b7de")
        call.enqueue(object : Callback<MoviesResponse> {

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){

                    val list: List<movie>
                    list= response.body()!!.results!!

                    for (data in list){

                        adapter= MyListAdapter(applicationContext,list)
                        recyclerView.adapter=adapter
                        adapter.notifyDataSetChanged()
                    }

                    return
                }else{


                    Toast.makeText(applicationContext,"\t"+response.message().toString(),Toast.LENGTH_SHORT).show()




                }




            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


    override fun onBackPressed() {
        super.onBackPressed()

        val intent:Intent= Intent()
        setResult(2,intent)
        finish()
    }

}
