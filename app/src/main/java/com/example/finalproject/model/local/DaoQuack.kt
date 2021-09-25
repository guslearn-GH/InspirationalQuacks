package com.example.finalproject.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.finalproject.model.QuackResponse

@Dao
interface DaoQuack {
    @Insert
    fun saveDataIntoCache(data: QuackEntities)
    @Query("SELECT * FROM quack_table WHERE id = :id " )
    fun getQuack(id:Int): QuackResponse
}