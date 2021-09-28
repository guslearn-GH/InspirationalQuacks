package com.example.finalproject.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalproject.QuackApplication
import com.example.finalproject.ViewModel.QuackViewModel

@Database(entities = [Quack::class], version =1, exportSchema=true)
abstract class QuackDatabase:RoomDatabase() {

    abstract fun getDao():QuackDao



    companion object{
        private var INSTANCE:QuackDatabase? =null
        fun newInstance(context: Context):QuackDatabase{
            var temp = INSTANCE
            if(temp != null) return temp

            synchronized(this){
                if(temp != null) return temp as QuackDatabase

                temp = Room.databaseBuilder(
                    context,
                    QuackDatabase::class.java,
                    "quack_room_db"
                )
                    .build()
                INSTANCE = temp

                return  temp as QuackDatabase
            }
        }
    }
}