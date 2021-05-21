package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;

import java.util.Date;
import java.util.List;

public interface MareuApiService {

    /**
     * returns the unfiltered list of all meetings
     * @return
     */
    public List<Meeting> getAllMeetings();

    /**
     * returns a list of all rooms
     * @return
     */
    public List<Room> getAllRooms();

    /**
     * adds a meeting
     * @param meeting
     */
    public void addNewMeeting(Meeting meeting);

    /**
     * deletes a meeting
     * @param meeting
     */
    public void removeMeeting(Meeting meeting);

    /**
     * returns a filtered list of meetings for the given room
     * @param room
     * @return
     */
    public List<Meeting> filterByRoom(Room room);

    /**
     * returns a filtered list of meetings for the given date
     * @param date
     * @return
     */
    public List<Meeting> filterByDate(Date date);

    /**
     * returns a list of all available rooms for the given time interval
     * @param start
     * @param end
     * @return
     */
    public List<Room> getAvailableRooms (Date start, Date end);


}
