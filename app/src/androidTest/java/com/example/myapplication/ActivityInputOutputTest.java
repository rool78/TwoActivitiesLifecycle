package com.example.myapplication;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.Activity;
import android.content.Context;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.android.twoactivities.MainActivity;
import com.example.android.twoactivities.R;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {


    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes.
     */
    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.android.twoactivities", appContext.getPackageName());
    }

    @Test
    public void activityLaunch() {
        onView(withId(R.id.button_main)).perform(click());
        onView(withId(R.id.text_header)).check(matches(isDisplayed()));
        onView(withId(R.id.button_second)).perform(click());
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()));
    }

    @Test
    public void testInputOutput() {
        onView(withId(R.id.editText_main)).perform(typeText("delivering text"));
        onView(withId(R.id.button_main)).perform(click());

        onView(withId(R.id.text_message)).check(matches(withText("delivering text")));
        onView(withId(R.id.editText_second)).perform(typeText("text reply"));
        onView(withId(R.id.button_second)).perform(click());

        onView(withId(R.id.text_message_reply)).check(matches(withText("text reply")));
    }

    @Test
    public void spinnerItemsIterate() {
        String[] items = {"Home", "Work", "Mobile", "Other"};

        for (String item : items) {
            onView(withId(R.id.label_spinner)).perform(click());
            onData(is(item)).perform(click());
        }

//        activityScenarioRule.getScenario().onActivity(activity -> {
//            items = activity.getResources().getStringArray(R.array.labels_array);
//        });

    }
}