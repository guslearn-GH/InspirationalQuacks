package com.example.finalproject.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quack")
data class Quack (
    @PrimaryKey(autoGenerate = true)
    var Id:Int=0,
    var Image:String="/",
    var Message:String="Tough Luckv2"
)
//data class tableQuack(
//    @PrimaryKey(autoGenerate = true)
//    val id:Int=0,
//    val image:String,
//    val message:String
//
//)
