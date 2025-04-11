package com.sitestart.blog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.HashMap;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName, secondName, password;

    private HashMap<MessageFromTo, ArrayList<String>> messengers;

    private ArrayList<String> messages;

    public User(String firstName, String secondName, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        messengers = new HashMap<>();
        messages = new ArrayList<>();
    }
    public User() {
    }


    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void addANewMessage() {

    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<MessageFromTo, ArrayList<String>> getMessengers() {
        return messengers;
    }
    public void refactor(MessageFromTo messageFromTo, String message) {
        ArrayList<String> messages = messengers.get(messageFromTo);
        messages.add(message);
        messengers.replace(messageFromTo, messages);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMessengers(HashMap<MessageFromTo, ArrayList<String>> messengers) {
        this.messengers = messengers;
    }
}
