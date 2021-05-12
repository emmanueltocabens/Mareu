package com.lamzone.mareu.events;

import com.lamzone.mareu.model.Meeting;

public class DeleteMeetingEvent {

    public final Meeting meeting;
    public DeleteMeetingEvent(Meeting meeting){
        this.meeting = meeting;
    }
}
