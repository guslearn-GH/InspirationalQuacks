package com.example.finalproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DaoQuack::class], version = 1)
abstract class QuackDatabase : RoomDatabase() {

    abstract fun getDao(): DaoQuack

    companion object{
        @Volatile
        private var instance : QuackDatabase? = null
        fun getInstace(context: Context):QuackDatabase{
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