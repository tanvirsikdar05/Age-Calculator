package com.smart_tech_nk.age_calculator;

import android.graphics.Bitmap;


public class recyclerviewexdata {
    private Bitmap imagesrc;
    private String name;
    private String eventtype;
    private String eventdate;
    private String age;
    private String nextdate;
    private String nexteventtype;
    private String dbid;

    public recyclerviewexdata(Bitmap image, String name, String eventtype, String eventdate, String age,
                              String nextdate, String nexteventtype, String dbidd) {
        this.imagesrc=image;
        this.name = name;
        this.eventtype = eventtype;
        this.eventdate = eventdate;
        this.age = age;
        this.nextdate = nextdate;
        this.nexteventtype = nexteventtype;
        this.dbid = dbidd;
    }

    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImagesrc() {
        return imagesrc;
    }

    public String getEventtype() {
        return eventtype;
    }

    public String getEventdate() {
        return eventdate;
    }

    public String getAge() {
        return age;
    }

    public String getNextdate() {
        return nextdate;
    }

    public String getNexteventtype() {
        return nexteventtype;
    }

    public void setImagesrc(Bitmap imagesrc) {
        this.imagesrc = imagesrc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setNextdate(String nextdate) {
        this.nextdate = nextdate;
    }

    public void setNexteventtype(String nexteventtype) {
        this.nexteventtype = nexteventtype;
    }
}
