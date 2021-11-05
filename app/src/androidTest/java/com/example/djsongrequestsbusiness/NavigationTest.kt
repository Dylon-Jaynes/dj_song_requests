package com.example.djsongrequestsbusiness

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.djsongrequestsbusiness.ui.MainActivity
import com.example.djsongrequestsbusiness.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationTest {

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testFragmentForwardNavigation() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Nav to SongListFragment
        onView(withId(R.id.fragment_song_list_parent))
            .check(matches(isDisplayed()))


        // Nav to LoginFragment
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.fragment_login_parent))
            .check(matches(isDisplayed()))


        // Nav to WelcomeFragment
        onView(withId(R.id.button_register)).perform(click())
        onView(withId(R.id.fragment_welcome_parent))
            .check(matches(isDisplayed()))


        // Nav to SetIdFragment
        onView(withId(R.id.button_continue)).perform(click())
        onView(withId(R.id.fragment_set_id_parent))
            .check(matches(isDisplayed()))


        // Nav to DisplayIdFragment
        onView(withId(R.id.button_next)).perform(click())
        onView(withId(R.id.fragment_display_id_parent))
            .check(matches(isDisplayed()))


        // Nav to ReceiveRequestsFragment
        onView(withId(R.id.button_next)).perform(click())
        onView(withId(R.id.fragment_receive_requests_parent))
            .check(matches(isDisplayed()))


        // Nav to BasicInfoFragment
        onView(withId(R.id.button_get_started)).perform(click())
        onView(withId(R.id.fragment_basic_info_parent))
            .check(matches(isDisplayed()))


        // Nav to DjIdFragment
        registerIdlingResource()
        onView(withId(R.id.edittext_username)).perform(typeText("Notatrace"))
        closeSoftKeyboard()
        onView(withId(R.id.edittext_email)).perform(typeText("fake_email1@gmail.com"))
        closeSoftKeyboard()
        onView(withId(R.id.edittext_password)).perform(typeText("mySecurePass"))
        closeSoftKeyboard()

        onView(withId(R.id.button_continue)).perform(click())
        unregisterIdlingResource()
        onView(withId(R.id.fragment_dj_id_parent))
            .check(matches(isDisplayed()))

        // Nav to SongListFragment
        onView(withId(R.id.button_send)).perform(click())
        onView(withId(R.id.fragment_song_list_parent))
            .check(matches(isDisplayed()))
    }
}