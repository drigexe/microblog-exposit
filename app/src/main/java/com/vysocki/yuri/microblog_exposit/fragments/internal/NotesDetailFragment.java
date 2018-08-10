package com.vysocki.yuri.microblog_exposit.fragments.internal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vysocki.yuri.microblog_exposit.Note;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.SharedViewModel;

import java.util.ArrayList;

public class NotesDetailFragment extends Fragment {

    SharedViewModel viewModel;

    TextView textView1;
    TextView textView2;
    Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_detail, container, false);

        textView1 = view.findViewById(R.id.themeDetail);
        textView2 = view.findViewById(R.id.dateDetail);
        button = view.findViewById(R.id.sendButton);

        Observer<Note> noteObserver = new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                // update the UI
            }
        };

        viewModel.getNote().observe(this, noteObserver);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //viewModel.setNote(noteArrayList);
            }
        });

        return view;
    }
}
