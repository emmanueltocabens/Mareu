package com.lamzone.mareu.events;

import com.lamzone.mareu.model.Meeting;

/**
 * fired when a meeting is deleted
 */
public class DeleteMeetingEvent {

    public final Meeting meeting;
    public DeleteMeetingEvent(Meeting meeting){
        this.meeting = meeting;
    }
}
