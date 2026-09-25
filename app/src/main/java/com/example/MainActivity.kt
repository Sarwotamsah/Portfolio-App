package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioData
import com.example.ui.components.shareText
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.ExperienceScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProjectsScreen
import com.example.ui.screens.ServicesScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.PortfolioTab
import com.example.ui.viewmodel.PortfolioViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: PortfolioViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val isDarkThemeOverride by viewModel.isDarkThemeOverride.collectAsState()
            val systemDark = isSystemInDarkTheme()
            val useDark = isDarkThemeOverride ?: systemDark

            MyApplicationTheme(darkTheme = useDark) {
                val selectedTab by viewModel.selectedTab.collectAsState()
                val snackbarHostState = remember { SnackbarHostState() }
                val context = LocalContext.current

                // Listen to UI Events
                LaunchedEffect(Unit) {
                    viewModel.uiEvents.collect { message ->
                        snackbarHostState.showSnackbar(message)
                    }
                }

                // Handle system back navigation when not on Home
                BackHandler(enabled = selectedTab != PortfolioTab.HOME) {
                    viewModel.selectTab(PortfolioTab.HOME)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = when (selectedTab) {
                                        PortfolioTab.HOME -> PortfolioData.NAME
                                        PortfolioTab.PROJECTS -> "Projects"
                                        PortfolioTab.EXPERIENCE -> "Experience & Skills"
                                        PortfolioTab.SERVICES -> "Services & Pricing"
                                        PortfolioTab.CONTACT -> "Contact & Collaborate"
                                    },
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = (-0.3).sp
                                    )
                                )
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        shareText(
                                            context,
                                            "Check out Sarwotam Sah's portfolio: ${PortfolioData.WEBSITE_URL}\n" +
                                                "Software Developer, AI Automation Analyst & SEO Expert.",
                                            "Share Portfolio"
                                        )
                                    },
                                    modifier = Modifier.testTag("share_app_btn")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = "Share Portfolio"
                                    )
                                }

                                IconButton(
                                    onClick = { viewModel.toggleTheme() },
                                    modifier = Modifier.testTag("toggle_theme_btn")
                                ) {
                                    Icon(
                                        imageVector = if (useDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                                        contentDescription = "Toggle Theme"
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                titleContentColor = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    },
                    bottomBar = {
                        PortfolioBottomBar(
                            currentTab = selectedTab,
                            onTabSelected = { viewModel.selectTab(it) }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .widthIn(max = 700.dp)
                        ) {
                            when (selectedTab) {
                                PortfolioTab.HOME -> {
                                    val bookmarkedIds by viewModel.bookmarkedProjectIds.collectAsState()
                                    HomeScreen(
                                        viewModel = viewModel,
                                        bookmarkedIds = bookmarkedIds,
                                        onNavigateTab = { viewModel.selectTab(it) },
                                        onOpenProject = { viewModel.openProjectDetails(it) }
                                    )
                                }

                                PortfolioTab.PROJECTS -> {
                                    val filteredProjects by viewModel.filteredProjects.collectAsState()
                                    val searchQuery by viewModel.searchQuery.collectAsState()
                                    val selectedCategory by viewModel.selectedCategory.collectAsState()
                                    val showOnlyBookmarked by viewModel.showOnlyBookmarked.collectAsState()
                                    val bookmarkedIds by viewModel.bookmarkedProjectIds.collectAsState()
                                    val selectedProjectForDetails by viewModel.selectedProject.collectAsState()

                                    ProjectsScreen(
                                        viewModel = viewModel,
                                        projects = filteredProjects,
                                        searchQuery = searchQuery,
                                        selectedCategory = selectedCategory,
                                        showOnlyBookmarked = showOnlyBookmarked,
                                        bookmarkedIds = bookmarkedIds,
                                        selectedProjectForDetails = selectedProjectForDetails,
                                        onOpenProject = { viewModel.openProjectDetails(it) },
                                        onCloseProjectDetails = { viewModel.closeProjectDetails() }
                                    )
                                }

                                PortfolioTab.EXPERIENCE -> {
                                    ExperienceScreen(viewModel = viewModel)
                                }

                                PortfolioTab.SERVICES -> {
                                    ServicesScreen(
                                        viewModel = viewModel,
                                        onNavigateTab = { viewModel.selectTab(it) }
                                    )
                                }

                                PortfolioTab.CONTACT -> {
                                    ContactScreen(viewModel = viewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PortfolioBottomBar(
    currentTab: PortfolioTab,
    onTabSelected: (PortfolioTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .testTag("portfolio_bottom_navigation"),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp
    ) {
        val items = listOf(
            Triple(PortfolioTab.HOME, Icons.Default.Home, Icons.Outlined.Home),
            Triple(PortfolioTab.PROJECTS, Icons.Default.Code, Icons.Outlined.Code),
            Triple(PortfolioTab.EXPERIENCE, Icons.Default.Work, Icons.Outlined.Work),
            Triple(PortfolioTab.SERVICES, Icons.Default.Calculate, Icons.Outlined.Calculate),
            Triple(PortfolioTab.CONTACT, Icons.Default.Email, Icons.Outlined.Email)
        )

        items.forEach { (tab, filledIcon, outlinedIcon) ->
            val isSelected = currentTab == tab
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) filledIcon else outlinedIcon,
                        contentDescription = tab.title
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}
