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

public class NotesDetailFragment extends Fragment {

    SharedViewModel viewModel;

    TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_detail, container, false);

        textView = view.findViewById(R.id.detailtextview);

        Observer<Note> noteObserver = new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                //update UI
                textView.setText(note.getNoteText());
            }
        };

        viewModel.getNote().observe(this, noteObserver);

        return view;
    }
}
