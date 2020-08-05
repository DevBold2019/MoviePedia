package com.example.moviepedia.Fragments


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.Adapters.MovieAdapter
import com.example.moviepedia.Adapters.UpcomingAdapter
import com.example.moviepedia.Models.MoviesResponse
import com.example.moviepedia.Models.movie
import com.example.moviepedia.MoreDetailsActivity
import com.example.moviepedia.R
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
    lateinit var textView3: TextView
    lateinit var textView4: TextView
    lateinit var textView5: TextView
    lateinit var scrollView: ScrollView
    lateinit var constraint:ConstraintLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_home, container, false)
        val unicode:Int=0x1F525
        val boom=0x1F4A5
        val playing=0x1F4FA
        val videoRecorder=0x1F4F9

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
        textView2=view.findViewById( R.id.upcomingTitle)
        textView3=view.findViewById( R.id.decribe1)
        textView4=view.findViewById( R.id.nowPlaying)
        textView5=view.findViewById( R.id.decribe)

        scrollView=view.findViewById(R.id.contentPage)
        constraint=view.findViewById(R.id.progressLayout)

        //#1B1E23

        receycler.layoutManager= LinearLayoutManager(container.context, LinearLayoutManager.HORIZONTAL,false)
        receycler.setHasFixedSize(true)

        receycler1.layoutManager= LinearLayoutManager(container.context, LinearLayoutManager.HORIZONTAL,false)
        receycler1.setHasFixedSize(true)

        receycler2.layoutManager= LinearLayoutManager(container.context, LinearLayoutManager.HORIZONTAL,false)
        receycler2.setHasFixedSize(true)

        receycler3.layoutManager= LinearLayoutManager(container.context, LinearLayoutManager.HORIZONTAL,false)
        receycler3.setHasFixedSize(true)

        val flame=String(Character.toChars(unicode))
        val boomEmoji=String(Character.toChars(boom))
        val nowPlaying=String(Character.toChars(playing))
        val popular=String(Character.toChars(videoRecorder))



        textView2.text=" Upcoming $flame";
        textView3.text=" Recommended  $boomEmoji";
        textView4.text=" Now Playing $nowPlaying";
        textView5.text=" Popular $popular";

        retrofit=Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        checkForNetwork()


        return view


    }

    fun loadMovies(){

        loadPopularMovies()
        loadTopRated()
        UpcomingMovies()
        nowPlaying()


    }
    //Re usability purpose for
    fun  populateDialog(title:String,description:String,language:String,imagePath:String){

        dialog.show()

        val t1:TextView=dialog.findViewById(R.id.Titles)
        val t2:TextView=dialog.findViewById(R.id.Description)
        val t4:TextView=dialog.findViewById(R.id.Language)
        val imageView: ImageView =dialog.findViewById(R.id.DetailPic)


        t1.text=title
        t2.text=description
        t4.text=language
        Glide.with(context!!).load(imagePath).placeholder(R.drawable.loading).into(imageView)

        val imageView1: ImageView =dialog.findViewById(R.id.closeImage)
        imageView1.setOnClickListener(object : View.OnClickListener {

            override fun onClick(p0: View?) {

                dialog.dismiss()
            }


        })

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


                }
            }
            override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {

                print(t.message.toString())
                Toast.makeText(context!!,""+t.message, Toast.LENGTH_LONG).show()

            }
        })

    }

    //Will work on it later
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

                val list: List<movie>

                if (!response.isSuccessful){


                    return
                }

                list= response.body()!!.results!!

                for (data in list){

                    adapter= MovieAdapter(context!!,list)
                    receycler3.adapter=adapter
                    adapter.notifyDataSetChanged()


                }

            }
            override fun onFailure(call: Call<MoviesResponse?>, t: Throwable) {

                print(t.message.toString())
                Toast.makeText(context!!,""+t.message, Toast.LENGTH_LONG).show()


            }
        })


    }

    //For connectivity check if the  wifi/network is connected to the internet
    @SuppressLint("NewApi")
    private fun checkForNetwork(): Boolean {
        val cm :ConnectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val netInfo: Network? = cm.activeNetwork

        //if there's network we want to load more data
        if (netInfo != null ) {

            constraint.visibility=View.GONE
            scrollView.visibility=View.VISIBLE
            loadMovies()
            return true
        }

                constraint.visibility=View.VISIBLE
                scrollView.visibility=View.GONE


            Toast.makeText(context, "Check your network", Toast.LENGTH_LONG).show()
            loadCachedMovies();


        return false
    }

    private fun loadCachedMovies() {




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==1 ){
            Toast.makeText(context,"Back",Toast.LENGTH_SHORT).show()

        }

    }

    val unicode:Int=0x1F525



    fun getEmoji(unicode: Int): String {

        return String(Character.toChars(unicode))
    }

    fun toast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()

    }



}
