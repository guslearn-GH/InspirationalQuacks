package com.example.finalproject.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_quack")
data class tableQuack(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val image:String,
    val message:String

)
