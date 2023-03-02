package com.example.a1stzoomassignment.DataLayer

import androidx.room.*
import com.example.a1stzoomassignment.DataModels.Repo
import kotlinx.coroutines.flow.Flow


@Dao
interface RepoDao {

    @Query ("SELECT * from repo")
    fun getRepos(): Flow<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(repo: Repo)

    @Update
    suspend fun update(repo: Repo)

    @Delete
    suspend fun delete(repo:Repo)

}