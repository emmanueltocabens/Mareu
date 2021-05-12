package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FakeMareuApiService implements MareuApiService {

    private ArrayList<Meeting> meetingList = new ArrayList<>(FakeMareuGenerator.FAKE_MEETINGS);
    private ArrayList<Meeting> filtered_meetingList = new ArrayList<>(FakeMareuGenerator.FAKE_MEETINGS);
    private ArrayList<Room> rooms = new ArrayList<>(FakeMareuGenerator.FAKE_ROOMS);


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
        meetingList.add(meeting);
    }

    @Override
    public void removeMeeting(Meeting meeting){
        meetingList.remove(meeting);
    }

    @Override
    public List<Meeting> filterByRoom(Room room){
        filtered_meetingList.clear();
        for(Meeting tmp : meetingList){
            if(tmp.getRoom() == room) {
                filtered_meetingList.add(tmp);
            }
        }
        return filtered_meetingList;
    }

    @Override
    public List<Meeting> filterByDate(Date date){
        filtered_meetingList.clear();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date);
        for(Meeting tmp : meetingList){
            c2.setTime(tmp.getStartDate());
            if(c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
            && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
            && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH))
                filtered_meetingList.add(tmp);
        }
        return filtered_meetingList;
    }

    @Override
    public List<Room> getAvailableRooms(Date start, Date end){
        List<Room> ret = new ArrayList<>(getAllRooms());
        for(Meeting tmp : getMeetings()){
            if(tmp.getStartDate().before(start) || tmp.getEndDate().before(end)){
                ret.remove(tmp.getRoom());
            }
        }
        return ret;
    }
}
