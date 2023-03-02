package com.example.a1stzoomassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.a1stzoomassignment.DataModels.Repo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        val runnable = Runnable {
            val intent = Intent(this, RepoActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        val iv = this.findViewById<ImageView>(R.id.logo_iv)
        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_screen)
        iv.startAnimation(animation)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, 2500)


    }
}