package com.lamzone.mareu.utils;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MareuUtils {

    static final String DATE_FORMAT_TIME = "HH:mm";
    static final String DATE_FORMAT_DATE = "dd-MM-yyyy";

    /**
     * returns a list of all room names
     * used for display in spinners
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
     * returns a string for the given meeting
     * used for display in recyclerview elements
     * @param meeting
     * @return
     */
    public static String getMeetingDisplayName(Meeting meeting){
        StringBuilder str = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTime(meeting.getStartDate());
        str.append(meeting.getTitle());
        str.append(" - ");
        str.append(getTimeString(c.getTime()));
        str.append(" - ");
        str.append(meeting.getRoom().getName());
        return str.toString();
    }

    /**
     * returns a string containing all participants mail addresses.
     * used for display.
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

    /**
     * returns a string using the static value DATE_FORMAT_DATE format for the given date.
     * used for display.
     */
    public static String getDateString(Date date){
        StringBuilder sb = new StringBuilder();
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_DATE, Locale.getDefault());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        sb.append(format.format(c.getTime()));
        return sb.toString();
    }

    /**
     * returns a string using the static value DATE_FORMAT_TIME format for the given date.
     * used for display.
     * @param date
     * @return
     */
    public static String getTimeString(Date date){
        StringBuilder ret =  new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_TIME, Locale.getDefault());
        ret.append(format.format(c.getTime()));
        return ret.toString();
    }
}
