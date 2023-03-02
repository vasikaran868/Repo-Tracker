package com.example.a1stzoomassignment.DataLayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a1stzoomassignment.DataModels.Repo

@Database(entities = [Repo::class], version = 1, exportSchema = false)
abstract class RepoRoomDataBase: RoomDatabase() {
    abstract fun repoDao():RepoDao

    companion object{
        @Volatile
        private var INSTANCE: RepoRoomDataBase? = null

        fun getDatabase(context: Context): RepoRoomDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RepoRoomDataBase::class.java,
                    "repo_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE= instance
                instance
            }
        }
    }
}