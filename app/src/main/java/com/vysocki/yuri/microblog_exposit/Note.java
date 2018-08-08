package com.vysocki.yuri.microblog_exposit;

import java.util.Date;

public class Note {

    private int userId;
    private String noteTheme;
    private String noteText;
    private Date noteDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNoteTheme() {
        return noteTheme;
    }

    public void setNoteTheme(String noteTheme) {
        this.noteTheme = noteTheme;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

}
