package com.example.agendaws.models;

public class Model_Event {
    String idEvent;
    String titleEvent;
    String categoryEvent;
    String dateEvent;
    String timeEvent;
    String idUser;

    public Model_Event() {
        this.idEvent = "id";
        this.timeEvent = "name";
        this.categoryEvent = "event";
        this.dateEvent = "date";
        this.timeEvent = "time";
        this.idUser = "idUser";
    }
    public Model_Event(String idEvent, String titleEvent, String categoryEvent, String dateEvent, String timeEvent, String idUser) {
        this.idEvent = idEvent;
        this.titleEvent = titleEvent;
        this.categoryEvent = categoryEvent;
        this.dateEvent = dateEvent;
        this.timeEvent = timeEvent;
        this.idUser = idUser;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitleEvent() {
        return titleEvent;
    }

    public void setTitleEvent(String titleEvent) {
        this.titleEvent = titleEvent;
    }
    public String getCategoryEvent() {
        return categoryEvent;
    }

    public void setCategoryEvent(String categoryEvent) {
        this.categoryEvent = categoryEvent;
    }
    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(String timeEvent) {
        this.timeEvent = timeEvent;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }



}
