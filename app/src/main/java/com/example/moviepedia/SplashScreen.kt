package com.example.moviepedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splas_screen)



      val handler:Handler=Handler()

        var intent:Intent

        handler.postDelayed(object : Runnable {

            override fun run() {

                intent=Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()


            }

        },3000)



    }
}
