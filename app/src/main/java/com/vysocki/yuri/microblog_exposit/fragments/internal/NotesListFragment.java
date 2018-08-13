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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.vysocki.yuri.microblog_exposit.MainActivity;
import com.vysocki.yuri.microblog_exposit.Note;
import com.vysocki.yuri.microblog_exposit.NotesRecyclerViewAdapter;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.RecyclerItemClickListener;
import com.vysocki.yuri.microblog_exposit.SharedViewModel;
import com.vysocki.yuri.microblog_exposit.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                mNoteDates.add(note.getDate().toString());
                mNoteThemes.add(note.getTheme());
            }
            initRecyclerView(getView());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
