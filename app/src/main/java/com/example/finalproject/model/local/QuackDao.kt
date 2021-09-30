package com.example.finalproject.model.local

import android.database.Cursor
import androidx.room.*

import io.reactivex.rxjava3.core.Observable

@Dao
interface QuackDao {

    @Insert(//entity = Quack::class,
    onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuack(q:Quack)

    @Query(value = "SELECT * FROM quack")
    suspend fun getOldQuacks(): List<Quack>

    @Query("DELETE FROM quack")
    suspend fun deleteOldQuacks()
}