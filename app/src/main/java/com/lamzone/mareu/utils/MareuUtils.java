package com.lamzone.mareu.utils;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MareuUtils {

    /**
     * retourne une liste des noms des salles disponibles
     * @param roomList
     * @return
     */
    public static List<String> getRoomNames(List<Room> roomList){
        ArrayList<String> ret = new ArrayList<>();
        for(Room tmp : roomList){
            ret.add(tmp.getName());
        }
        return ret;
    }

    /**
     * retourne un nom d'affichage pour la réunion donnée
     * @param meeting
     * @return
     */
    public static String getMeetingDisplayName(Meeting meeting){
        StringBuilder str = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTime(meeting.getStartDate());
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        str.append(meeting.getTitle());
        str.append(" - ");
        if(hours < 10)
            str.append("0");
        str.append(hours);
        str.append("h");
        if(minutes < 10)
            str.append("0");
        str.append(minutes);
        str.append(" - ");
        str.append(meeting.getRoom().getName());
        return str.toString();
    }

    /**
     * retourne les adresses mails des participants pour les afficher
     * @return
     */
    public static String getParticipantsAddresses(Meeting meeting){
        StringBuilder ret = new StringBuilder();
        for(String tmp : meeting.getParticipants()){
            ret.append(tmp);
            ret.append(", ");
        }
        ret.deleteCharAt(ret.length()-1);
        ret.deleteCharAt(ret.length()-1);
        return ret.toString();
    }

    public static String getDateString(int year, int month, int dayOfMonth){

        StringBuilder sb = new StringBuilder();
        if(dayOfMonth<10)
            sb.append("0");
        sb.append(dayOfMonth);
        sb.append("/");
        if(month<10)
            sb.append("0");
        sb.append(month);
        sb.append("/");
        sb.append(year);
        return sb.toString();
    }

    public static String getTimeString(int hourOfDay, int minute){
        StringBuilder ret =  new StringBuilder();
        if(hourOfDay < 10)
            ret.append("0");
        ret.append(hourOfDay).append(":");
        if(minute < 10)
            ret.append("0");
        ret.append(minute);
        return ret.toString();
    }
}
