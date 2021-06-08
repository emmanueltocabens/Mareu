package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;

import java.util.Date;
import java.util.List;

public interface MareuApiService {

    /**
     * returns the unfiltered list of all meetings
     * @return list of all meetings
     */
    List<Meeting> getAllMeetings();

    /**
     * returns a list of all rooms
     * @return a list of all rooms
     */
    List<Room> getAllRooms();

    /**
     * adds a meeting
     * @param meeting to be added
     */
    void addNewMeeting(Meeting meeting);

    /**
     * deletes a meeting
     * @param meeting to be deleted
     */
    void removeMeeting(Meeting meeting);

    /**
     * returns a filtered list of meetings for the given room
     * @param room that will be used to filter
     * @return a list of meetings that happen in that room
     */
    List<Meeting> filterByRoom(Room room);

    /**
     * returns a filtered list of meetings for the given date
     * @param date meeting date (does not consider time)
     * @return list of meetings that happen that day
     */
    List<Meeting> filterByDate(Date date);

    /**
     * returns a list of all available rooms for the given time interval
     * @param start meeting start date and time
     * @param end meeting end date and time
     * @return List of available rooms
     */
    List<Room> getAvailableRooms (Date start, Date end);


}
