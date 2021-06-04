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
    public List<Meeting> getAllMeetings();

    /**
     * returns a list of all rooms
     * @return a list of all rooms
     */
    public List<Room> getAllRooms();

    /**
     * adds a meeting
     * @param meeting to be added
     */
    public void addNewMeeting(Meeting meeting);

    /**
     * deletes a meeting
     * @param meeting to be deleted
     */
    public void removeMeeting(Meeting meeting);

    /**
     * returns a filtered list of meetings for the given room
     * @param room
     * @return a list of meetings that happen in that room
     */
    public List<Meeting> filterByRoom(Room room);

    /**
     * returns a filtered list of meetings for the given date
     * @param date meeting date (does not consider time)
     * @return
     */
    public List<Meeting> filterByDate(Date date);

    /**
     * returns a list of all available rooms for the given time interval
     * @param start meeting start date and time
     * @param end meeting end date and time
     * @return List of available rooms
     */
    public List<Room> getAvailableRooms (Date start, Date end);


}
