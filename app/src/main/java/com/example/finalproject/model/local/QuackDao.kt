package com.example.finalproject.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import io.reactivex.rxjava3.core.Observable

@Dao
interface QuackDao {

    @Insert(entity = tableQuack::class,
    onConflict = OnConflictStrategy.REPLACE)
    fun insertQuack(q:tableQuack)

//    @Query(value = "SELECT 1 FROM table_quack")
//    fun getFirstQuack(): Observable<tableQuack>
}