package com.example.finalproject.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoQuack {
    @Insert
    fun saveDataIntoCache(data: QuackEntities)
    @Query("SELECT * FROM quack_table WHERE id = :id " )
    fun getQuack(id:Int):QuackResponse
}