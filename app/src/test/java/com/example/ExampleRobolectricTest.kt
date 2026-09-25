package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.PortfolioData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Sarwotam Sah", appName)
    }

    @Test
    fun `portfolio data is correctly loaded`() {
        assertEquals("Sarwotam Sah", PortfolioData.NAME)
        assertTrue(PortfolioData.PROJECTS.isNotEmpty())
        assertTrue(PortfolioData.EXPERIENCES.isNotEmpty())
        assertTrue(PortfolioData.SERVICES.isNotEmpty())
    }
}
