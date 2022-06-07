package io.appeiron.clientapp.presentation

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.appeiron.clientapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun test_views_displayed_correctly() {

        launchActivity<MainActivity>()

        //Check connect button exists with correct txt
        onView(withId(R.id.btnConnect)).check(matches(withText(R.string.response)))

        //Check head text exists with correct txt
        onView(withId(R.id.txtHeadline)).check(matches(withText(R.string.sample_client)))

        //Check response text exists with correct txt
        onView(withId(R.id.txtResponse)).check(matches(withText(R.string.no_response)))


    }
}