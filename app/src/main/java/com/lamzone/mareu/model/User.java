package com.lamzone.mareu.model;

public class User {

    private String mailAddress;

    /**
     * constructeur d'un utilisateur
     * @param mailAddress adresse mail du participant
     */
    public User(String mailAddress){
        this.mailAddress = mailAddress;
    }

    //getters et setters
    public String getMailAddress() {
        return mailAddress;
    }
}
