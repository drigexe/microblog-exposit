package com.vysocki.yuri.microblog_exposit.fragments.internal;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vysocki.yuri.microblog_exposit.MainActivity;
import com.vysocki.yuri.microblog_exposit.Note;
import com.vysocki.yuri.microblog_exposit.NotesRecyclerViewAdapter;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.SharedViewModel;

import java.util.ArrayList;

public class NotesListFragment extends Fragment {

    private static final String TAG = "NotesListFragment";

    private ArrayList<String> mNoteThemes = new ArrayList<>();
    private ArrayList<String> mNoteDates = new ArrayList<>();

    private SharedViewModel viewModel;

    private Note note = new Note();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);

        initNoteArrays();
        initRecyclerView(view);

        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setNote(note);
            }
        });*/

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).toggleDrawer(false);
    }

    public void initNoteArrays() {
        //get mote items from viewmodel and add them to the mNoteThemes and mNoteDates
        mNoteThemes.add("kokoko");
        mNoteDates.add("kekasaeke");
        mNoteThemes.add("kokdsaaoko");
        mNoteDates.add("kekhgjheke");
        mNoteThemes.add("kodfgfdkoko");
        mNoteDates.add("kek54545eke");
        mNoteThemes.add("kokoko");
        mNoteDates.add("kekasaeke");
        mNoteThemes.add("kokdsaaoko");
        mNoteDates.add("kekhgjheke");
        mNoteThemes.add("kodfgfdkoko");
        mNoteDates.add("kek54545eke");
        mNoteThemes.add("kokoko");
        mNoteDates.add("kekasaeke");
        mNoteThemes.add("kokdsaaoko");
        mNoteDates.add("kekhgjheke");
        mNoteThemes.add("kodfgfdkoko");
        mNoteDates.add("kek54545eke");
        mNoteThemes.add("kokoko");
        mNoteDates.add("kekasaeke");
        mNoteThemes.add("kokdsaaoko");
        mNoteDates.add("kekhgjheke");
        mNoteThemes.add("kodfgfdkoko");
        mNoteDates.add("kek54545eke");
        mNoteThemes.add("kokoko");
        mNoteDates.add("kekasaeke");
        mNoteThemes.add("kokdsaaoko");
        mNoteDates.add("kekhgjheke");
        mNoteThemes.add("kodfgfdkoko");
        mNoteDates.add("kek54545eke");
    }

    public void initRecyclerView(View view) {
        Log.d(TAG, "initRecyclerView: init recyclerview.");

        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        NotesRecyclerViewAdapter adapter = new NotesRecyclerViewAdapter(getActivity(), mNoteThemes, mNoteDates);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
