package com.example.model

enum class ProjectCategory(val label: String) {
    ALL("All"),
    WEB_DEV("Web Dev"),
    AI_ML("AI / ML"),
    STARTUPS("Startups"),
    SEO_TOOLS("SEO & Automation")
}

data class Project(
    val id: String,
    val title: String,
    val tagline: String,
    val category: ProjectCategory,
    val description: String,
    val keyFeatures: List<String>,
    val techStack: List<String>,
    val liveDemoUrl: String,
    val githubUrl: String,
    val isFeatured: Boolean,
    val impactMetric: String
)

data class ExperienceItem(
    val id: String,
    val role: String,
    val organization: String,
    val period: String,
    val location: String,
    val description: String,
    val keyDeliverables: List<String>,
    val skills: List<String>,
    val isCurrent: Boolean
)

data class SkillItem(
    val name: String,
    val levelPercentage: Float,
    val levelLabel: String,
    val tag: String
)

data class SkillCategoryGroup(
    val categoryName: String,
    val iconKey: String,
    val skills: List<SkillItem>
)

data class ServiceItem(
    val id: String,
    val title: String,
    val tagline: String,
    val priceTag: String,
    val description: String,
    val features: List<String>,
    val deliveryTime: String,
    val cta: String
)

data class Testimonial(
    val id: String,
    val quote: String,
    val author: String,
    val role: String,
    val relation: String
)

data class EstimateOption(
    val id: String,
    val title: String,
    val description: String,
    val priceUsd: Int,
    val days: Int,
    val category: String
)

data class ContactInquiryData(
    val name: String,
    val email: String,
    val serviceType: String,
    val message: String
)
