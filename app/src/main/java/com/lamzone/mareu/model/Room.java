package com.lamzone.mareu.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private static int nbSalles = 0;

    private int id; //identifiant de la salle
    private String name; //nom de la salle
    private int color;

    /**
     * Constructeur d'une salle
     * par défaut la salle est libre
     * @param roomName
     */
    public Room(String roomName, int color){
        this.id = nbSalles;
        nbSalles++;
        this.name = roomName;
        this.color = color;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor(){
        return this.color;
    }

}
