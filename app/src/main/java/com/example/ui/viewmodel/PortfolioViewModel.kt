package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.PortfolioData
import com.example.data.db.PortfolioDatabase
import com.example.data.db.SavedInquiryEntity
import com.example.data.repository.PortfolioRepository
import com.example.model.EstimateOption
import com.example.model.ExperienceItem
import com.example.model.Project
import com.example.model.ProjectCategory
import com.example.model.ServiceItem
import com.example.model.SkillCategoryGroup
import com.example.model.Testimonial
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class PortfolioTab(val title: String) {
    HOME("Home"),
    PROJECTS("Projects"),
    EXPERIENCE("Experience"),
    SERVICES("Services"),
    CONTACT("Contact")
}

data class ContactFormState(
    val name: String = "",
    val email: String = "",
    val serviceType: String = "General Inquiry",
    val message: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val messageError: String? = null,
    val isSubmitted: Boolean = false
)

class PortfolioViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PortfolioRepository

    init {
        val db = PortfolioDatabase.getDatabase(application)
        repository = PortfolioRepository(db.portfolioDao())
    }

    // Active bottom navigation tab
    private val _selectedTab = MutableStateFlow(PortfolioTab.HOME)
    val selectedTab: StateFlow<PortfolioTab> = _selectedTab.asStateFlow()

    // Dark theme override (null = follow system, true = dark, false = light)
    private val _isDarkThemeOverride = MutableStateFlow<Boolean?>(null)
    val isDarkThemeOverride: StateFlow<Boolean?> = _isDarkThemeOverride.asStateFlow()

    // Projects Filtering
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow(ProjectCategory.ALL)
    val selectedCategory: StateFlow<ProjectCategory> = _selectedCategory.asStateFlow()

    private val _showOnlyBookmarked = MutableStateFlow(false)
    val showOnlyBookmarked: StateFlow<Boolean> = _showOnlyBookmarked.asStateFlow()

    val bookmarkedProjectIds: StateFlow<List<String>> = repository.getBookmarkedProjectIds()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val savedInquiries: StateFlow<List<SavedInquiryEntity>> = repository.getAllInquiries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Detailed project bottom sheet selection
    private val _selectedProject = MutableStateFlow<Project?>(null)
    val selectedProject: StateFlow<Project?> = _selectedProject.asStateFlow()

    // Filtered projects flow
    val filteredProjects: StateFlow<List<Project>> = combine(
        _searchQuery,
        _selectedCategory,
        _showOnlyBookmarked,
        bookmarkedProjectIds
    ) { query, category, onlyBookmarked, bookmarks ->
        PortfolioData.PROJECTS.filter { project ->
            val matchesQuery = query.isBlank() ||
                project.title.contains(query, ignoreCase = true) ||
                project.description.contains(query, ignoreCase = true) ||
                project.techStack.any { it.contains(query, ignoreCase = true) }

            val matchesCategory = category == ProjectCategory.ALL || project.category == category
            val matchesBookmark = !onlyBookmarked || bookmarks.contains(project.id)

            matchesQuery && matchesCategory && matchesBookmark
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PortfolioData.PROJECTS
    )

    // Estimator options selection
    private val _selectedEstimateOptionIds = MutableStateFlow<Set<String>>(
        setOf("opt_web_mvp", "opt_ai_integration")
    )
    val selectedEstimateOptionIds: StateFlow<Set<String>> = _selectedEstimateOptionIds.asStateFlow()

    // Contact form state
    private val _contactForm = MutableStateFlow(ContactFormState())
    val contactForm: StateFlow<ContactFormState> = _contactForm.asStateFlow()

    // One-time UI events (Snackbars, Intent triggers)
    private val _uiEvents = MutableSharedFlow<String>()
    val uiEvents: SharedFlow<String> = _uiEvents.asSharedFlow()

    fun selectTab(tab: PortfolioTab) {
        _selectedTab.value = tab
    }

    fun toggleTheme() {
        _isDarkThemeOverride.update { current ->
            when (current) {
                null -> true
                true -> false
                false -> null
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setCategory(category: ProjectCategory) {
        _selectedCategory.value = category
    }

    fun setShowOnlyBookmarked(only: Boolean) {
        _showOnlyBookmarked.value = only
    }

    fun toggleBookmark(projectId: String) {
        viewModelScope.launch {
            repository.toggleBookmark(projectId)
        }
    }

    fun openProjectDetails(project: Project) {
        _selectedProject.value = project
    }

    fun closeProjectDetails() {
        _selectedProject.value = null
    }

    fun toggleEstimateOption(optionId: String) {
        _selectedEstimateOptionIds.update { current ->
            if (current.contains(optionId)) {
                current - optionId
            } else {
                current + optionId
            }
        }
    }

    fun getEstimatedTotals(): Pair<Int, Int> {
        val options = PortfolioData.ESTIMATE_OPTIONS.filter {
            _selectedEstimateOptionIds.value.contains(it.id)
        }
        val totalPrice = options.sumOf { it.priceUsd }
        val totalDays = options.maxOfOrNull { it.days } ?: 0
        return Pair(totalPrice, totalDays)
    }

    fun applyEstimateToContact() {
        val options = PortfolioData.ESTIMATE_OPTIONS.filter {
            _selectedEstimateOptionIds.value.contains(it.id)
        }
        val (price, days) = getEstimatedTotals()
        val optionTitles = options.joinToString(", ") { it.title }
        val prefilledMsg = "Hi Sarwotam,\n\nI configured an estimate for: $optionTitles.\n" +
            "Estimated budget: $$price\nEstimated timeline: ~$days days.\n\n" +
            "I'd like to discuss kickstarting this project!"

        _contactForm.update {
            it.copy(
                serviceType = "Freelance Build ($optionTitles)",
                message = prefilledMsg
            )
        }
        _selectedTab.value = PortfolioTab.CONTACT
        viewModelScope.launch {
            _uiEvents.emit("Estimate applied to inquiry form!")
        }
    }

    fun updateContactName(name: String) {
        _contactForm.update { it.copy(name = name, nameError = null) }
    }

    fun updateContactEmail(email: String) {
        _contactForm.update { it.copy(email = email, emailError = null) }
    }

    fun updateContactServiceType(service: String) {
        _contactForm.update { it.copy(serviceType = service) }
    }

    fun updateContactMessage(message: String) {
        _contactForm.update { it.copy(message = message, messageError = null) }
    }

    fun submitContactForm(onSuccess: (name: String, email: String, service: String, message: String) -> Unit) {
        val state = _contactForm.value
        var hasError = false
        var nameErr: String? = null
        var emailErr: String? = null
        var msgErr: String? = null

        if (state.name.trim().isBlank()) {
            nameErr = "Please enter your name"
            hasError = true
        }

        if (state.email.trim().isBlank() || !state.email.contains("@")) {
            emailErr = "Please enter a valid email address"
            hasError = true
        }

        if (state.message.trim().isBlank()) {
            msgErr = "Please enter your message or project requirements"
            hasError = true
        }

        if (hasError) {
            _contactForm.update {
                it.copy(
                    nameError = nameErr,
                    emailError = emailErr,
                    messageError = msgErr
                )
            }
            return
        }

        viewModelScope.launch {
            repository.saveInquiry(
                name = state.name.trim(),
                email = state.email.trim(),
                serviceType = state.serviceType,
                message = state.message.trim()
            )
            _contactForm.update { it.copy(isSubmitted = true) }
            _uiEvents.emit("Inquiry recorded! Preparing email client...")
            onSuccess(state.name, state.email, state.serviceType, state.message)
        }
    }

    fun resetContactSubmission() {
        _contactForm.update {
            ContactFormState()
        }
    }

    fun deleteSavedInquiry(id: Long) {
        viewModelScope.launch {
            repository.deleteInquiry(id)
        }
    }

    // Static data helpers
    fun getExperiences(): List<ExperienceItem> = PortfolioData.EXPERIENCES
    fun getSkills(): List<SkillCategoryGroup> = PortfolioData.SKILL_GROUPS
    fun getServices(): List<ServiceItem> = PortfolioData.SERVICES
    fun getTestimonials(): List<Testimonial> = PortfolioData.TESTIMONIALS
    fun getEstimateOptions(): List<EstimateOption> = PortfolioData.ESTIMATE_OPTIONS
}
