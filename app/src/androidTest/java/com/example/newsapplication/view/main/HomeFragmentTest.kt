package com.example.newsapplication.view.main


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsapplication.CountingIdlingResourceSingleton
import com.example.newsapplication.R
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeFragmentTest {

    @get:Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance()
            .register(CountingIdlingResourceSingleton.countingIdlingResource)

    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance()
            .unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    /**
     * Recycler comes into view
     */
    @Test
    fun A_isHomeFragmentIsVisible() {
        onView(withId(R.id.news_rv)).check(matches(isDisplayed()))
    }

    /**
     * Recycler scroll and click item and navigate to Detail Fragment
     */
    @Test
    fun B_scrollToItemAndClick() {
        onView(withId(R.id.news_rv)).perform(
            actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(
                7,
                click()
            )
        )
        onView(withId(R.id.title)).check(matches(isDisplayed()))
    }

    /**
     * Recycler scroll and click item and navigate to Detail Fragment and press back
     */
    @Test
    fun C_clickBackInDetailFragment() {
        onView(withId(R.id.news_rv)).perform(
            actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(
                7,
                click()
            )
        )
        onView(withId(R.id.back_iv)).perform(click())
        onView(withId(R.id.news_rv)).check(matches(isDisplayed()))
    }
}