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
            new Room("Salle 1", Color.RED),
            new Room("Salle 2", Color.BLUE),
            new Room("Salle 3", Color.GREEN),
            new Room("Salle 4", Color.GRAY),
            new Room("Salle 5", Color.CYAN),
            new Room("Salle 6", Color.BLACK),
            new Room("Salle 7", Color.YELLOW),
            new Room("Salle 8", Color.MAGENTA),
            new Room("Salle 9", Color.WHITE),
            new Room("Salle de conférence", Color.DKGRAY)
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
