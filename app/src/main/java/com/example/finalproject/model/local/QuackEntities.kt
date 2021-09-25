package com.example.finalproject.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quack_table")
data class QuackEntities (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @ColumnInfo(name="url")
    var url:String,
    @ColumnInfo(name="message")
    var message:String
){
}