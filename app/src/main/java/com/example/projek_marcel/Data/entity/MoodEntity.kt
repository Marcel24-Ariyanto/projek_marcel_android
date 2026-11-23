package com.example.projek_marcel.Data.entity
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "moods")
data class  MoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val img: Int,
    val date: Long,
    val createAt: Long = System.currentTimeMillis()
)