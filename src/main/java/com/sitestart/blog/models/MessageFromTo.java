package com.sitestart.blog.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class MessageFromTo implements Serializable, Comparable<MessageFromTo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userFrom;
    private String userTo;

    private String message;

    private String time;
    private String exactTime;

    public MessageFromTo() {}

    public MessageFromTo(String userFrom, String userTo, String message) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.message = message;
        time = "yyyy.MM.dd HH:mm:ss";
        exactTime = "HH:mm";
    }

    public String getTime() {
        return time;
    }

    public String getExactTime() {
        return exactTime;
    }

    public void setExactTime(String exactTime) {
        this.exactTime = exactTime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "From: " + userFrom + " \nTo: " + userTo + "Message: " + getMessage();
    }

    @Override
    public int compareTo(MessageFromTo o) {
        return o.getTime().compareTo(this.getTime());
    }
}
