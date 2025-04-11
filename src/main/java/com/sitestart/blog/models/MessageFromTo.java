package com.sitestart.blog.models;



public class MessageFromTo {
    private String userFrom;
    private String userTo;
    public MessageFromTo(String userFrom, String userTo) {
        this.userFrom = userFrom;
        this.userTo = userTo;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageFromTo that = (MessageFromTo) o;

        if (!userFrom.equals(that.userFrom)) return false;
        return userTo.equals(that.userTo);
    }

    @Override
    public int hashCode() {
        int result = userFrom.hashCode();
        result = 31 * result + userTo.hashCode();
        return result;
    }


    public String toString() {
        return "From: " + getUserFrom() + "\nTo: " + getUserTo();
    }
}
