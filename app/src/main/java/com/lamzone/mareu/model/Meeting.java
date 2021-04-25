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

    public Meeting (Date starts, Date ends, Room room, List<String> participants, String subject){
        this.startDate = starts;
        this.endDate = ends;
        this.room = room;
        this.participants = participants;
        this.title = subject;
    }

    //getters and setters

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getMeetingDisplayName(){
        StringBuilder str = new StringBuilder();
        Calendar.getInstance().setTime(startDate);
        int hours = Calendar.getInstance().get(Calendar.HOUR);
        int minutes = Calendar.getInstance().get(Calendar.MINUTE);
        str.append(title);
        str.append(" - ");
        str.append(hours);
        str.append("h");
        str.append(minutes);
        str.append(" - ");
        return str.toString();

    }

    public String getParticipantsAddresses(){
        StringBuilder ret = new StringBuilder();
        for(String tmp : participants){
            ret.append(tmp);
            ret.append(", ");
        }
        ret.deleteCharAt(ret.length()-1);
        ret.deleteCharAt(ret.length()-1);
        return ret.toString();
    }
}
