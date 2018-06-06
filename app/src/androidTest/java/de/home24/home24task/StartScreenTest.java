package de.home24.home24task;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import de.home24.home24task.ui.StartScreenActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StartScreenTest {

    @Rule
    public ActivityTestRule<StartScreenActivity> mActivityRule = new ActivityTestRule<>(
            StartScreenActivity.class);


    @Before
    public void setUp() throws Exception {
        //Before Test case execution
    }

    @Test
    public void testBtnPressed() {
        onView(withId(R.id.bStart)).check(matches(isDisplayed()));
        onView(withId(R.id.bStart)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        //After Test case Execution
    }
}
