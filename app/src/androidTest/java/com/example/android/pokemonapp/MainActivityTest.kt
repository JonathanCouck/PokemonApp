package com.example.android.pokemonapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.android.pokemonapp.screens.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkHomeScreenViewsPresent() {
        onView(withText("Welcome to my little application for finding any pokemon you like, and a little bit of information about it!"))
            .check(matches(isDisplayed()))

        onView(withId(R.id.logo_iv))
            .check(matches(isDisplayed()))

        onView(withId(R.id.search_button))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkNaturalFlowOfSearchPokemon() {
        onView(withId(R.id.search_button))
            .perform(click())

        onView(withId(R.id.search_input))
            .check(matches(isDisplayed()))

        onView(withId(R.id.search_input))
            .perform()
            .perform(typeText("pidge"))

        onView(withId(R.id.search_pokemon_button))
            .perform(click())
        Thread.sleep(2000)

        onView(withId(R.id.pokemon_recycler)).perform(ViewActions.swipeUp());

        onView(withId(R.id.pokemon_recycler))
            .check(matches(hasChildCount(3)))

        Espresso.pressBack()

        onView(withId(R.id.search_button))
            .check(matches(isDisplayed()))
    }
}