package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;

import java.util.Date;
import java.util.List;

public interface MareuApiService {

    /**
     * retourne la liste des réunions
     * @return
     */
    public List<Meeting> getMeetings();

    /**
     * retourne la liste de toutes les salles
     * @return
     */
    public List<Room> getAllRooms();

    /**
     * retourne la liste des salles disponibles
     * @return
     */
    public List<Room> getAvailableRooms(Date start, Date end);

    /**
     * returns the object Room with the specified ID
     * @param id
     * @return
     */
    public Room findRoomById(int id);



}
