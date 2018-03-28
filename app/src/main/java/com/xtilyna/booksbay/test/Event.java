package com.xtilyna.booksbay.test;


import com.xtilyna.booksbay.test.entities.Photo;

import java.util.HashMap;

public class Event {

    public final static int ON_MYBUTTON_CLICK = 1;
    public final static int ON_FAB_CLICK = 2;
    public final static int ON_DATA_FETCHED = 3;

    private int eventType;
    private String message;
    private HashMap<String, String> messageMap;
    private Photo[] photos;

    public Photo[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photo[] photos) {
        this.photos = photos;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public  void setMessage(HashMap<String, String> msg) {
        this.messageMap = msg;
    }

    public HashMap<String, String> getMessageMap() {
        return messageMap;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }
}
