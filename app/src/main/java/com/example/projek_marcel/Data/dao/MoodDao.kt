package com.example.projek_marcel.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.projek_marcel.Data.entity.MoodEntity
@Dao
interface MoodDao{
    @Query("SELECT * FROM moods ORDER BY createAt DESC")
    suspend fun getAll(): List<MoodEntity>
    @Insert
    suspend fun  insert(mood: MoodEntity)
    @Delete
    suspend fun delete(mood: MoodEntity)
}