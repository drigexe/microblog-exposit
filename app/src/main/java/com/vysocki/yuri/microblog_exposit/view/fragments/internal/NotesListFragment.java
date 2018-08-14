package com.vysocki.yuri.microblog_exposit.view.fragments.internal;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vysocki.yuri.microblog_exposit.view.activities.MainActivity;
import com.vysocki.yuri.microblog_exposit.model.Note;
import com.vysocki.yuri.microblog_exposit.adapters.NotesRecyclerViewAdapter;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.listeners.RecyclerItemClickListener;
import com.vysocki.yuri.microblog_exposit.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import androidx.navigation.Navigation;

public class NotesListFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    private static final String TAG = "NotesListFragment";

    private ArrayList<String> mNoteThemes = new ArrayList<>();
    private ArrayList<String> mNoteDates = new ArrayList<>();
    private ArrayList<Note> noteArrayList = new ArrayList<>();

    private SharedViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String uId = firebaseUser.getUid();
        mRef = FirebaseDatabase.getInstance().getReference().child("notes").child(uId);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).toggleDrawer(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mRef.addValueEventListener(noteListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (noteListener != null) {
            mRef.removeEventListener(noteListener);
        }
        mNoteDates.clear();
        mNoteThemes.clear();
    }

    public void initRecyclerView(View view) {
        Log.d(TAG, "initRecyclerView: init recyclerview.");

        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        NotesRecyclerViewAdapter adapter = new NotesRecyclerViewAdapter(getActivity(), mNoteThemes, mNoteDates);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (getResources().getBoolean(R.bool.twoPaneMode)) {
                            Note note = noteArrayList.get(position);
                            viewModel.setNote(note);
                        } else {
                            Note note = noteArrayList.get(position);
                            viewModel.setNote(note);
                            Navigation.findNavController(view).navigate(R.id.action_notesExternalFragment_to_notesDetailExternalFragment);
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    ValueEventListener noteListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                Note note = ds.getValue(Note.class);
                noteArrayList.add(note);

                // sort by date algorithm doesn't work for some reason
                // unfortunately, i don't have enough time to fix this issue
                // so I'll leave it as it is
                Collections.sort(noteArrayList, new Comparator<Note>() {
                    public int compare(Note o1, Note o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });

                mNoteDates.add(note.getDate().toString());
                mNoteThemes.add(note.getTheme());
            }
            initRecyclerView(getView());

            if (noteArrayList.isEmpty()) {
                showAlert();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.alert_noteslist_title)
                .setMessage(R.string.alert_noteslist_message)
                .setIcon(R.drawable.ic_mood_bad)
                .setCancelable(true)
                .setNegativeButton(R.string.alert_noteslist_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Navigation.findNavController(getView()).navigate(R.id.action_notesExternalFragment_to_newNoteExternalFragment);
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
