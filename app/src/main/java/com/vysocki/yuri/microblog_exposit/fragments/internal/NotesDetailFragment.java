package com.vysocki.yuri.microblog_exposit.fragments.internal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vysocki.yuri.microblog_exposit.MainActivity;
import com.vysocki.yuri.microblog_exposit.Note;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.SharedViewModel;

import androidx.navigation.Navigation;

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
        final View view = inflater.inflate(R.layout.fragment_notes_detail, container, false);

        noteThemeText = view.findViewById(R.id.themeDetailEditText);
        noteText = view.findViewById(R.id.textDetailEditText);
        saveButton = view.findViewById(R.id.saveNoteButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //need to send data to the server here
                Toast.makeText(getActivity(),"Note created!", Toast.LENGTH_SHORT).show();
                lockUi(true);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).toggleDrawer(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (viewModel.getNote().getValue() != null) {
            lockUi(true);
        } else {
            lockUi(false);
        }

        Observer<Note> noteObserver = new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                if (viewModel.getNote().getValue() != null) {
                    //when note is set
                    lockUi(true);
                    noteThemeText.setText(note.getNoteTheme());
                    noteText.setText(note.getNoteText());
                } else {
                    // when note is cleared
                    clearEditTexts();
                    lockUi(false);
                }
            }
        };

        viewModel.getNote().observe(this, noteObserver);

    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.clearNote();
        Log.i("ONPAUSE", "ONPAUSE HAPPENED");
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
