package com.example.moviedbapplication.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MovieLocalData::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase  :  RoomDatabase(){
    abstract fun MovieDao() : MovieDao
    companion object{
        @Volatile
        private var INSTANCE : MovieDatabase?= null
        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "Movie_Database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}