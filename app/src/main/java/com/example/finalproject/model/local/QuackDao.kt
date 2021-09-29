package com.example.finalproject.model.local

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import io.reactivex.rxjava3.core.Observable

@Dao
interface QuackDao {

    @Insert(//entity = Quack::class,
    onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuack(q:Quack)

    @Query(value = "SELECT * FROM quack")
    suspend fun getOldQuacks(): List<Quack>
}