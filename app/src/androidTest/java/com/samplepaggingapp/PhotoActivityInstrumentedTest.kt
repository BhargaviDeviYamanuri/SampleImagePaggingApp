package com.samplepaggingapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.samplepaggingapp.view.PhotoActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotoActivityInstrumentedTest {
    @get:Rule
    val photoActivityRule = ActivityTestRule(PhotoActivity::class.java)

    @Test
    fun testSearchPhoto() {
        onView(withId(R.id.etSearchKey)).perform(typeText("India"))
            .perform(ViewActions.pressImeActionButton())
        val searchResultView: RecyclerView =
            (photoActivityRule.activity).findViewById(R.id.rvSearchResult)
        val resultCount = searchResultView.adapter?.itemCount
        Assert.assertEquals(resultCount, 0)
    }

    @Test
    fun testResult() {
        onView(withId(R.id.etSearchKey)).perform(typeText("India"))
            .perform(ViewActions.pressImeActionButton())
        Thread.sleep(5000)
        val searchResultView: RecyclerView =
            (photoActivityRule.activity).findViewById(R.id.rvSearchResult)
        val resultCount = searchResultView.adapter?.itemCount
        Assert.assertNotEquals(resultCount, 0)
    }

}