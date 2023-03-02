package com.example.a1stzoomassignment

import android.app.Application
import com.example.a1stzoomassignment.DataLayer.RepoRoomDataBase

class RepoApplication: Application(){
    val database: RepoRoomDataBase by lazy {
        RepoRoomDataBase.getDatabase(this)
    }
}