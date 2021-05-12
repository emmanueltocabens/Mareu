package com.lamzone.mareu.events;

import com.lamzone.mareu.model.Room;

public class RoomSelectedEvent {
    public Room room;

    public RoomSelectedEvent(Room room){
        this.room = room;
    }
}
