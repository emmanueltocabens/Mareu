package com.lamzone.mareu.utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.lamzone.mareu.R;

import org.hamcrest.Matcher;

public class DeleteViewAction implements ViewAction {
        @Override
        public Matcher<View> getConstraints() {
            return null;
        }

        @Override
        public String getDescription() {
            return "Click on specific button";
        }

        @Override
        public void perform(UiController uiController, View view) {
            View button = view.findViewById(R.id.delete_meeting_imageButton);
            // Maybe check for null
            button.performClick();
        }
}

