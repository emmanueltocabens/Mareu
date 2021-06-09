package com.lamzone.mareu.ui;

import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.utils.DeleteViewAction;
import com.lamzone.mareu.utils.RecyclerViewMatcher;
import com.lamzone.mareu.utils.RecyclerViewUtils;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * User Interface Tests
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListUI {

    private int baseMeetingSize = 0;

    @Rule
    public ActivityScenarioRule<MareuListActivity> mActivityRule = new ActivityScenarioRule<>(MareuListActivity.class);

    @Before
    public void init(){
        mActivityRule.getScenario().onActivity(MareuListActivity::useNewApiService);
        baseMeetingSize = DependencyInjector.getMareuApiService().getAllMeetings().size();
    }

    /**
     * Assert that recycler view is displayed correctly, then delete a meeting, then checks if the number of meetings displayed is correct
     */
    @Test
    public void deleteMeetingTest(){
        onView(withId(R.id.recyclerView)).check(new RecyclerViewUtils.ItemCount(baseMeetingSize));
        onView(allOf(
                isDisplayed(),
                withClassName(is("androidx.appcompat.widget.AppCompatImageButton")),
                withParent(withParent(RecyclerViewUtils.withRecyclerView(R.id.recyclerView).atPosition(3))),
                withId(R.id.delete_meeting_imageButton)
        )).perform(click());

        onView(withId(R.id.recyclerView)).check(new RecyclerViewUtils.ItemCount(baseMeetingSize-1));
    }

    /**
     * navigate to addMeetingActivity, and go back to MeetingListActivity
     */
    @Test
    public void navigationTest(){
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_add_meeting)).perform(ViewActions.click());
        onView(withId(R.id.add_constraint_layout)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

    /**
     * navigate to addMeetingActivity and create a new meeting
     */
    @Test
    public void addNewMeetingTest(){
        //navigate to addmeeting
        onView(withId(R.id.fab_add_meeting)).perform(ViewActions.click());

        //fill title
        onView(withId(R.id.add_et_title)).perform(ViewActions.typeText("Test meeting title"));

        //open date/time pickers and confirm
        onView(withId(R.id.add_et_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.add_et_time_start)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.add_et_time_end)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        //add 3 new participants
        onView(withId(R.id.add_et_participants)).perform(ViewActions.typeText("participant1@somemail.com"));
        onView(withId(R.id.button_add_participant)).perform(click());
        onView(withId(R.id.add_et_participants)).perform(ViewActions.typeText("participant2@somemail.com"));
        onView(withId(R.id.button_add_participant)).perform(click());
        onView(withId(R.id.add_et_participants)).perform(ViewActions.typeText("participant3@somemail.com"));
        onView(withId(R.id.add_et_participants)).perform(closeSoftKeyboard());

        //change room
        onView(withId(R.id.room_spinner)).perform(click());
        onData(instanceOf(String.class)).atPosition(3).perform(click());

        //add the meeting
        onView(withId(R.id.confirm_meeting_button)).perform(click());

        //make sure we are back to home screen
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

    /**
     * click on overflow menu button, chose date filter, and assert that correct amount of meetings is displayed
     */
    @Test
    public void testFilterDate(){
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Filtrer par date")).perform(click());
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.recyclerView)).check(new RecyclerViewUtils.ItemCount(6));
    }

    /**
     * click on overflow menu button, chose room filter, and assert that correct amount of meetings is displayed
     */
    @Test
    public void testFilterRoom(){
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Filtrer par salle")).perform(click());
        onView(withText("Salle 1")).inRoot(RootMatchers.isDialog()).perform(click());
        onView(withId(R.id.recyclerView)).check(new RecyclerViewUtils.ItemCount(2));
    }

    /**
     * click on overflow menu button, chose no filter, and assert that correct amount of meetings is displayed
     */
    @Test
    public void testNoFilter(){
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Toutes les réunions")).perform(click());
        onView(withId(R.id.recyclerView)).check(new RecyclerViewUtils.ItemCount(6));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}