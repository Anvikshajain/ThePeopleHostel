package com.example.thepeoplehostel;

class VisitorEntry {
    public String name;
    public String date;
    public String number;

    public VisitorEntry() {
    }

    public VisitorEntry(String name, String date, String number) {
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
