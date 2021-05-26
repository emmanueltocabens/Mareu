package com.lamzone.mareu;

import android.app.Activity;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.lamzone.mareu.ui.MareuListActivity;
import com.lamzone.mareu.utils.BaseActivity;
import com.lamzone.mareu.utils.MareuApplication;
import com.lamzone.mareu.utils.RecyclerViewUtils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


/**
 * Unit Tests
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(AndroidJUnit4.class)
@RunWith(RobolectricTestRunner.class)
@LargeTest
public class MareuIntrumentedTest {

    private int currentMeetingSize = -1;


    @Rule
    public ActivityScenarioRule<MareuListActivity> mActivityRule = new ActivityScenarioRule<>(MareuListActivity.class);

    @Before
    public void init(){
        ActivityScenario<MareuListActivity> scenario = mActivityRule.getScenario();
        BaseActivity activity = (BaseActivity) Robolectric.setupActivity(MareuListActivity.class);
        currentMeetingSize = activity.getApiService().getAllMeetings().size();
        //Activity activity =
        
    }

    @Test
    public void checkIfRecyclerViewIsFilled(){
        onView(withId(R.id.recyclerView)).check(new RecyclerViewUtils.ItemCount(currentMeetingSize));
    }

}