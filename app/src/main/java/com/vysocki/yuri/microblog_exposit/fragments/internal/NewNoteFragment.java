package com.vysocki.yuri.microblog_exposit.fragments.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vysocki.yuri.microblog_exposit.MainActivity;
import com.vysocki.yuri.microblog_exposit.R;

import androidx.navigation.Navigation;

public class NewNoteFragment extends Fragment {

    EditText theme;
    EditText text;
    Button saveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_new_note, container, false);

        theme = view.findViewById(R.id.themeNewNoteEditText);
        text = view.findViewById(R.id.textNewNoteEditText);
        saveButton = view.findViewById(R.id.saveNewNoteButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText.getTheme and getText
                // GetCurrentDate
                // GetUserId
                // send all of that stuff to the server
                // on success make toast, clear fields and redirect to NotesExternalFragment
                Toast.makeText(getActivity(),"Note created!", Toast.LENGTH_SHORT).show();
                theme.setText(null);
                text.setText(null);
                Navigation.findNavController(view).navigate(R.id.action_newNoteExternalFragment_to_notesExternalFragment);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).toggleDrawer(false);
    }

}