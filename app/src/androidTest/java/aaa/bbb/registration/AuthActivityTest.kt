package aaa.bbb.registration

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AuthActivityTest {
    @get:Rule
    var activityActivityTestRule = ActivityTestRule(
        AuthActivity::class.java
    )

    @Test
    fun validateEditText() {
        onView(withId(R.id.eT4)).perform(typeText("Hello")).check(matches(withText("Hello")))
    }
    @Test
    fun validateEditText2() {
        onView(withId(R.id.eT5)).perform(typeText("Hello")).check(matches(withText("Hello")))
    }
}