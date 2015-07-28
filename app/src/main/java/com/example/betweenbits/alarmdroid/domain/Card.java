package com.example.betweenbits.alarmdroid.domain;


import java.io.Serializable;

/**
 * Created by brunov0id on 21/07/15.
 */
public class Card implements Serializable {
    private long id;
    private String clock;
    private String title;
    private Boolean status;

    public Card(String clock, String title, Boolean status) {
        this.clock = clock;
        this.title = title;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
