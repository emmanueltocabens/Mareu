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
                    makeNewDate(9,0),
                    makeNewDate(10,0),
                    FAKE_ROOMS.get(0),
                    FAKE_USERS_1,
                    "Réunion A"),

            new Meeting(
                    makeNewDate(13,30),
                    makeNewDate(14,0),
                    FAKE_ROOMS.get(1),
                    FAKE_USERS_2,
                    "Réunion B"),

            new Meeting(
                    makeNewDate(10,0),
                    makeNewDate(12,0),
                    FAKE_ROOMS.get(2),
                    FAKE_USERS_3,
                    "Réunion C"),

            new Meeting(
                    makeNewDate(10,0),
                    makeNewDate(11,0),
                    FAKE_ROOMS.get(0),
                    FAKE_USERS_1,
                    "Réunion D"),

            new Meeting(
                    makeNewDate(16,0),
                    makeNewDate(17,0),
                    FAKE_ROOMS.get(1),
                    FAKE_USERS_2,
                    "Réunion E"),

            new Meeting(
                    makeNewDate(16,0),
                    makeNewDate(17,0),
                    FAKE_ROOMS.get(2),
                    FAKE_USERS_3,
                    "Réunion F")
    );


    /**
     * creates a new Date object using current date and the given hour/min
     * @param hour
     * @param min
     * @return
     * used for fake data entries
     */
    private static Date makeNewDate(int hour, int min){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        return c.getTime();
    }

    public static class testDate {
        private final Calendar calendar;
        private final Date date;

        public testDate(int hour, int min){
            calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, min);
            date = calendar.getTime();
        }

        public Date getDate() {
            return date;
        }
    }


}
