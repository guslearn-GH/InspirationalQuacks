package com.example.finalproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuackEntities::class], version = 1, exportSchema = true)
abstract class QuackDatabase : RoomDatabase() {

    abstract fun getDao(): DaoQuack

    companion object{
        @Volatile
        private var instance : QuackDatabase? = null
        fun getInstance(context: Context):QuackDatabase{
            val tempInstance = instance
            if(tempInstance != null)
                return tempInstance
            synchronized(this){
                val instanceRoom = Room.databaseBuilder(
                    context.applicationContext,
                    QuackDatabase::class.java,
                    "quack_db")
                    .build()
                instance = instanceRoom
                return  instanceRoom
            }
        }
    }
}