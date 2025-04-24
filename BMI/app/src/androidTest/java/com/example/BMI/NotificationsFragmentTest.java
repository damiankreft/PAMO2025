package com.example.BMI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NotificationsFragmentTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenario =
            new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void can_calculate_benedict_harris() {
        onView(withId(R.id.navigation_recommendations)).perform(click());
        onView(withId(R.id.heightEditText)).perform(typeText("1.8"));
        onView(withId(R.id.weightEditText)).perform(typeText("77"));
        onView(withId(R.id.bmiTextView)).check(matches(withText("23.765, healthy")));
    }
}
