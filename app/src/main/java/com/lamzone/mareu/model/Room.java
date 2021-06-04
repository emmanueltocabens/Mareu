package com.lamzone.mareu.model;

public class Room {

    private static int nbSalles = 0;//count rooms to generate ids

    private int id; //id of the room
    private String name; //room display name
    private final int color;//imageView color for this room

    /**
     * Constructor for a room
     * @param roomName display name of the room
     * @param color color that will help recognise the room
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
