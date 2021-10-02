package com.example.movieapps.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.movieapps.R
import com.example.movieapps.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val movie = dummyMovies[0]
    private val dummyTv = DataDummy.generateTvs()
    private val tv = dummyTv[0]

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size)
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(movie.title)))

        onView(withId(R.id.text_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.text_rating)).check(matches(withText(movie.popularity.toString())))

        onView(withId(R.id.text_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(withText(movie.overview)))

        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(movie.releaseDate)))

        onView(withId(R.id.text_vote)).check(matches(isDisplayed()))
        onView(withId(R.id.text_vote)).check(matches(withText(movie.voteCount.toString())))
    }

    @Test
    fun loadTv() {
        onView(withText("Tv")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size)
        )
    }

    @Test
    fun loadDetailTv() {
        onView(withText("Tv")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(tv.name)))

        onView(withId(R.id.text_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.text_rating)).check(matches(withText(tv.popularity.toString())))

        onView(withId(R.id.text_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(withText(tv.overview)))

        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(tv.firstAirDate)))

        onView(withId(R.id.text_vote)).check(matches(isDisplayed()))
        onView(withId(R.id.text_vote)).check(matches(withText(tv.voteCount.toString())))
    }

}