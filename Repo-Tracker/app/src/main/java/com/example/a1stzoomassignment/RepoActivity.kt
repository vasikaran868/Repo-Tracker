package com.example.a1stzoomassignment

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class RepoActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        val navHostfragment = supportFragmentManager?.findFragmentById(R.id.repo_frag_container) as NavHostFragment
        navController = navHostfragment.navController
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.orange)))
        //supportActionBar?.hide()

    }
}