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
import android.widget.EditText;

import com.vysocki.yuri.microblog_exposit.Note;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.SharedViewModel;

public class NotesDetailFragment extends Fragment {

    SharedViewModel viewModel;

    Button saveButton;
    EditText noteThemeText;
    EditText noteText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_detail, container, false);

        noteThemeText = view.findViewById(R.id.themeDetailEditText);
        noteText = view.findViewById(R.id.textDetailEditText);
        saveButton = view.findViewById(R.id.saveNoteButton);

        Observer<Note> noteObserver = new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                if (viewModel.getNote().getValue() != null) {
                    lockUi(true);
                    noteThemeText.setText(note.getNoteTheme());
                    noteText.setText(note.getNoteText());
                } else {
                    clearEditTexts();
                }
            }
        };

        viewModel.getNote().observe(this, noteObserver);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clearEditTexts();
                viewModel.clearNote();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        lockUi(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.clearNote();
        clearEditTexts();
    }

    private void clearEditTexts() {
        noteThemeText.setText(null);
        noteText.setText(null);
    }

    private void lockUi (Boolean locked) {
        if (locked) {
            noteThemeText.setEnabled(false);
            noteText.setEnabled(false);
            saveButton.setVisibility(View.INVISIBLE);
        } else {
            noteThemeText.setEnabled(true);
            noteText.setEnabled(true);
            saveButton.setVisibility(View.VISIBLE);
        }
    }
}
