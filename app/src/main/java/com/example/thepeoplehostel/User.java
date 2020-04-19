package com.example.thepeoplehostel;

public class User {
    private String name;
    private String date;
    private String number;

    public User() {
    }

    public User(String name, String date, String number) {
        this.name = name;
        this.date = date;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
