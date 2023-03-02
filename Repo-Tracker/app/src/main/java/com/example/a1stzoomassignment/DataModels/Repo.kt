package com.example.a1stzoomassignment.DataModels

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Repo(
    @PrimaryKey(autoGenerate = true)
    var repoId :Int = 0,
    @ColumnInfo(name = "owner_name")
    var ownerName: String,
    @ColumnInfo(name = "repo_name")
    var repoName: String,
    @ColumnInfo(name = "repo_desc")
    var repoDescription: String):Parcelable
