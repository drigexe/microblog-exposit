package com.vysocki.yuri.microblog_exposit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Note> note = new MutableLiveData<>();

    public void setNote(Note note) {
        this.note.setValue(note);
    }

    public LiveData<Note> getNote() {
        return this.note;
    }

}

