package com.lamzone.mareu.service;

import android.graphics.Color;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;

import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

public abstract class FakeMareuGenerator {


    public static List<Integer> COLORS_LIST = Arrays.asList(
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.GRAY,
            Color.CYAN,
            Color.BLACK,
            Color.YELLOW,
            Color.MAGENTA,
            Color.WHITE,
            Color.DKGRAY
    );

    public static List<Room> FAKE_ROOMS = Arrays.asList(
            new Room("salle 1", Color.RED),
            new Room("salle 2", Color.BLUE),
            new Room("salle 3", Color.GREEN),
            new Room("salle 4", Color.GRAY),
            new Room("salle 5", Color.CYAN),
            new Room("salle 6", Color.BLACK),
            new Room("salle 7", Color.YELLOW),
            new Room("salle 8", Color.MAGENTA),
            new Room("salle 9", Color.WHITE),
            new Room("salle de conférence", Color.DKGRAY)
    );

    public static List<String> FAKE_USERS_1 = Arrays.asList(
            "fabrice@mareu.com",
           "eric@mareu.com",
            "ilies@mareu.com",
            "erwan@mareu.com",
            "charles@mareu.com");

    public static List<String> FAKE_USERS_2 = Arrays.asList(
            "ilies@mareu.com",
            "marie@mareu.com",
            "erwan@mareu.com");

    public static List<String> FAKE_USERS_3 = Arrays.asList(
           "charles@mareu.com",
            "anne@mareu.com",
            "paul@mareu.com");



    public static List<Meeting> FAKE_MEETINGS = Arrays.asList(
            new Meeting(
                    new SmartDate(9,30).getDate(),
                    new SmartDate(12,0).getDate(),
                    FAKE_ROOMS.get(0),
                    FAKE_USERS_1,
                    "Réunion A"),

            new Meeting(
                    new SmartDate(14,0).getDate(),
                    new SmartDate(16,0).getDate(),
                    FAKE_ROOMS.get(1),
                    FAKE_USERS_2,
                    "Réunion B"),

            new Meeting(
                    new SmartDate(12,30).getDate(),
                    new SmartDate(14,0).getDate(),
                    FAKE_ROOMS.get(2),
                    FAKE_USERS_3,
                    "Réunion C")
    );



    public static class SmartDate {
        private Calendar calendar = Calendar.getInstance();
        private Date date;

        public SmartDate(int hour, int min){
            calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, min);
            date = calendar.getTime();
        }

        public Date getDate() {
            return date;
        }
    }


}
