package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {

    @Query("SELECT projectId FROM bookmarked_projects")
    fun getBookmarkedProjectIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkProject(entity: BookmarkedProjectEntity)

    @Query("DELETE FROM bookmarked_projects WHERE projectId = :projectId")
    suspend fun removeBookmark(projectId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_projects WHERE projectId = :projectId)")
    fun isProjectBookmarked(projectId: String): Flow<Boolean>

    @Query("SELECT * FROM saved_inquiries ORDER BY timestamp DESC")
    fun getAllInquiries(): Flow<List<SavedInquiryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveInquiry(inquiry: SavedInquiryEntity)

    @Query("DELETE FROM saved_inquiries WHERE id = :id")
    suspend fun deleteInquiry(id: Long)
}
