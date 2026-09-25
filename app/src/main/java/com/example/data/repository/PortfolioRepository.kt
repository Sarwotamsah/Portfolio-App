package com.example.data.repository

import com.example.data.PortfolioData
import com.example.data.db.BookmarkedProjectEntity
import com.example.data.db.PortfolioDao
import com.example.data.db.SavedInquiryEntity
import com.example.model.EstimateOption
import com.example.model.ExperienceItem
import com.example.model.Project
import com.example.model.ServiceItem
import com.example.model.SkillCategoryGroup
import com.example.model.Testimonial
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class PortfolioRepository(private val dao: PortfolioDao) {

    fun getBookmarkedProjectIds(): Flow<List<String>> = dao.getBookmarkedProjectIds()

    suspend fun toggleBookmark(projectId: String) {
        val currentBookmarks = dao.getBookmarkedProjectIds().first()
        if (currentBookmarks.contains(projectId)) {
            dao.removeBookmark(projectId)
        } else {
            dao.bookmarkProject(BookmarkedProjectEntity(projectId))
        }
    }

    fun getAllInquiries(): Flow<List<SavedInquiryEntity>> = dao.getAllInquiries()

    suspend fun saveInquiry(name: String, email: String, serviceType: String, message: String) {
        dao.saveInquiry(
            SavedInquiryEntity(
                name = name,
                email = email,
                serviceType = serviceType,
                message = message
            )
        )
    }

    suspend fun deleteInquiry(id: Long) {
        dao.deleteInquiry(id)
    }

    fun getProjects(): List<Project> = PortfolioData.PROJECTS

    fun getExperiences(): List<ExperienceItem> = PortfolioData.EXPERIENCES

    fun getSkills(): List<SkillCategoryGroup> = PortfolioData.SKILL_GROUPS

    fun getServices(): List<ServiceItem> = PortfolioData.SERVICES

    fun getTestimonials(): List<Testimonial> = PortfolioData.TESTIMONIALS

    fun getEstimateOptions(): List<EstimateOption> = PortfolioData.ESTIMATE_OPTIONS
}
