package com.example.betweenbits.alarmdroid.domain;

import java.util.Date;

/**
 * Created by brunov0id on 21/07/15.
 */
public class Card {
    private long id;
    private String clock;
    private String title;
    private Boolean status;

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