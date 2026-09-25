package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_projects")
data class BookmarkedProjectEntity(
    @PrimaryKey val projectId: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_inquiries")
data class SavedInquiryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val serviceType: String,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)
