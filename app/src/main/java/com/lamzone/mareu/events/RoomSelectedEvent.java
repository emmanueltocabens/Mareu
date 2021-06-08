package com.lamzone.mareu.events;

import com.lamzone.mareu.model.Room;

/**
 * Event fired when the room filter button is pressed
 */
public class RoomSelectedEvent {
    public Room room;

    public RoomSelectedEvent(Room room){
        this.room = room;
    }
}
