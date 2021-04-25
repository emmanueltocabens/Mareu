package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;

import java.util.Date;
import java.util.List;

public class FakeMareuApiService implements MareuApiService {



    @Override
    public List<Meeting> getMeetings() {
        return FakeMareuGenerator.FAKE_MEETINGS;
    }

    @Override
    public List<Room> getAllRooms() {
        return FakeMareuGenerator.FAKE_ROOMS;
    }

    @Override
    public List<Room> getAvailableRooms(Date start, Date end) {
        return null;
    }

    public Room findRoomById(int id){
        for(Room tmp : FakeMareuGenerator.FAKE_ROOMS){
            if(tmp.getId() == id){
                return tmp;
            }
        }
        return null;
    }
}
