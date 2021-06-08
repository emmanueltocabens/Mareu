package com.lamzone.mareu.events;

import com.lamzone.mareu.model.Meeting;

/**
 * Event fired when the user presses on the delete_meeting_imageButton
 */
public class DeleteMeetingEvent {
    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting){
        this.meeting = meeting;
    }
}
