package com.lamzone.mareu.ui;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.service.MareuApiService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class addMeetingUI {

    private MareuApiService apiService;

    @Rule
    public ActivityScenarioRule<AddMeetingActivity> mActivityRule = new ActivityScenarioRule<>(AddMeetingActivity.class);

    @Before
    public void init(){
        ActivityScenario<AddMeetingActivity> scenario = mActivityRule.getScenario();
        apiService = DependencyInjector.getNewInstanceApiService();
    }


    @Test
    public void addParticipantsTest(){
        onView(withId(R.id.add_et_participants)).perform(ViewActions.typeText("participant@somemail.com"));
        onView(withId(R.id.button_add_participant)).perform(click());
        onView(withId(R.id.add_et_participants)).perform(ViewActions.typeText("anotherparticipant@somemail.com"));
        onView(withId(R.id.button_add_participant)).perform(click());
        onView(withId(R.id.layout_participants)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(2)));
    }

    @Test
    public void addNewMeetingTest(){
        onView(withId(R.id.add_et_title)).perform(ViewActions.typeText("Test meeting title"));

        onView(withId(R.id.add_et_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());


        onView(withId(R.id.add_et_participants)).perform(ViewActions.typeText("participant1@somemail.com"));
        onView(withId(R.id.button_add_participant)).perform(click());
        onView(withId(R.id.add_et_participants)).perform(ViewActions.typeText("participant2@somemail.com"));
        onView(withId(R.id.button_add_participant)).perform(click());
        onView(withId(R.id.add_et_participants)).perform(ViewActions.typeText("participant3@somemail.com"));
        onView(withId(R.id.button_add_participant)).perform(click());
    }
}
