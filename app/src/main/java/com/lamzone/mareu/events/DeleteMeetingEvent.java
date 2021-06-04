package com.lamzone.mareu.events;

import com.lamzone.mareu.model.Meeting;

/**
 * fired when delete imageButton is pressed
 */
public class DeleteMeetingEvent {

    public final Meeting meeting;
    public DeleteMeetingEvent(Meeting meeting){
        this.meeting = meeting;
    }
}
