package com.lamzone.mareu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Meeting implements Serializable {

    private final Date startDate;//date and time(hour/min) at which the meeting starts
    private final Date endDate;//date and time(hour/min) at which the meeting ends
    private final Room room; //room where the meeting takes place
    private final List<String> participants; //Participants' mail addresses
    private final String title; //display name/subject of the meeting

    public Meeting (Date starts, Date ends, Room room, List<String> participants, String title){
        this.startDate = starts;
        this.endDate = ends;
        this.room = room;
        this.participants = participants;
        this.title = title;
    }

    //getters and setters

    public Room getRoom() {
        return room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public String getTitle() {
        return title;
    }
}
