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

    /**
     * renvoie une liste filtrée des réunions qui ont lien dans la salle Room
     * @param room
     * @return
     */
    public List<Meeting> filterByRoom(Room room);

    /**
     * retourne une liste filtrée des réunions qui ont lieu pendant le créneau start-end
     * @param date
     * @return
     */
    public List<Meeting> filterByDate(Date date);

    /**
     * Retourne une liste filtrée des salles disponibles pendant le créneau start-end
     * @param start
     * @param end
     * @return
     */
    public List<Room> getAvailableRooms (Date start, Date end);


}
