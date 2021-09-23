package com.example.finalproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "quack_table")
class QuackEntities (
    @ColumnInfo(name="id")
    val id:Int,
    @ColumnInfo(name="url")
    val url:String,
    @ColumnInfo(name="message")
    val message:String
){
}