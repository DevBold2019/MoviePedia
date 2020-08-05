package com.example.moviepedia


import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.moviepedia.Fragments.ExploreFragment
import com.example.moviepedia.Fragments.HomeFragment
import com.example.moviepedia.Fragments.MyListFragment
import com.example.moviepedia.Fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    lateinit var  bottomNav: BottomNavigationView
    lateinit var toolbar: Toolbar
    lateinit var layout:ConstraintLayout
    //lateinit var flame:String=""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)



        bottomNav=findViewById(R.id.bottomNavigation)
        toolbar=findViewById(R.id.MainToolbar)
        setSupportActionBar(toolbar)


        // Flame Emoji U+1F525




        bottomNav.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {


                when(p0.itemId){

                    R.id.homeMenu ->{

                        val fragment:Fragment=HomeFragment()
                        supportActionBar!!.setTitle("Home")
                        supportFragmentManager.beginTransaction().replace(R.id.contentPage, fragment).commit()

                    }
                    R.id.profileMenu ->{

                        val fragment:Fragment=ProfileFragment()
                        supportActionBar!!.setTitle("My Account")
                        supportFragmentManager.beginTransaction().replace(R.id.contentPage, fragment).commit()


                    }

                    R.id.favoriteMenu ->{

                        val fragment:Fragment=MyListFragment()
                        supportActionBar!!.setTitle("My List")
                        supportFragmentManager.beginTransaction().replace(R.id.contentPage, fragment).commit()

                    }

                    R.id.browseMenu ->{

                        val fragment:Fragment=ExploreFragment()
                        supportActionBar!!.setTitle("Explore")
                        supportFragmentManager.beginTransaction().replace(R.id.contentPage, fragment).commit()

                    }





                }





                return true
            }


        })

        if (savedInstanceState==null){

           bottomNav.selectedItemId=R.id.homeMenu
        }


    }





}
