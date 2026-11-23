package com.example.projek_marcel.Mood

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projek_marcel.Data.entity.MoodEntity
import com.example.projek_marcel.Data.dao.MoodDao
@Database(
    entities = [MoodEntity::class],
    version =1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun moodDao(): MoodDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also {INSTANCE = it}
            }
    }
}