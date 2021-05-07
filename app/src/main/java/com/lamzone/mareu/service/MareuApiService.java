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
     * ajoute une nouvelle réunion
     * @param meeting
     */
    public void addNewMeeting(Meeting meeting);

    /**
     * supprime une réunion existante
     * @param meeting
     */
    public void removeMeeting(Meeting meeting);



}
