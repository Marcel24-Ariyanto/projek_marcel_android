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
    @Query("SELECT * FROM moods WHERE date >= :startTime ORDER BY date DESC")
    suspend fun getMoodsAfter(startTime: Long): List<MoodEntity>



    @Query("SELECT * FROM moods WHERE id = :moodId LIMIT 1")
    suspend fun getMoodById(moodId: Int): MoodEntity?
    @Insert
    suspend fun  insert(mood: MoodEntity)
    @Delete
    suspend fun delete(mood: MoodEntity)
}