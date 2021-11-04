package com.example.djsongrequestsbusiness

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.djsongrequestsbusiness.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationTest {

    @Test
    fun testFragmentsNavigation() {

        // SETUP
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Nav SongListFragment
        onView(withId(R.id.fragment_song_list_parent))
            .check(matches(isDisplayed()))

        // Nav LoginFragment
        onView(withId(R.id.button)).perform(click())

        onView(withId(R.id.fragment_login_parent))
            .check(matches(isDisplayed()))

        // Nav WelcomeFragment
        onView(withId(R.id.button_register)).perform(click())

        onView(withId(R.id.fragment_welcome_parent))
            .check(matches(isDisplayed()))

        // Nav SetIdFragment
        onView(withId(R.id.button_continue)).perform(click())

        onView(withId(R.id.fragment_set_id_parent))
            .check(matches(isDisplayed()))

        // Nav DisplayIdFragment
        onView(withId(R.id.button_next)).perform(click())

        onView(withId(R.id.fragment_display_id_parent))
            .check(matches(isDisplayed()))
    }
}