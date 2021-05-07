package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FakeMareuApiService implements MareuApiService {

    private List<Meeting> meetingList = FakeMareuGenerator.FAKE_MEETINGS;
    private List<Room> rooms = FakeMareuGenerator.FAKE_ROOMS;


    @Override
    public List<Meeting> getMeetings() {
        return meetingList;
    }

    @Override
    public List<Room> getAllRooms() {
        return rooms;
    }

    @Override
    public void addNewMeeting(Meeting meeting) {
        ArrayList<Meeting> arr = new ArrayList<>(meetingList);
        arr.add(meeting);
        meetingList = arr;
    }

    @Override
    public void removeMeeting(Meeting meeting){
        meetingList.remove(meeting);
    }
}
