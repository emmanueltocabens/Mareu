package com.lamzone.mareu.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Meeting implements Serializable {

    private Date startDate;
    private Date endDate;
    private Room room; //salle dans laquelle se déroule la réunion
    private List<String> participants; //Personnes qui participent à la réunion
    private String title; //sujet de la réunion

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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
