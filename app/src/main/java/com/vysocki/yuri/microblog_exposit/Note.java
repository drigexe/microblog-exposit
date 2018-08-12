package com.vysocki.yuri.microblog_exposit;

import java.util.Date;

public class Note {

    private String theme;
    private String text;
    private Date date;

    public Note() {

    }

    public Note(String noteid, String theme, String text, Date date) {
        this.theme = theme;
        this.text = text;
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
