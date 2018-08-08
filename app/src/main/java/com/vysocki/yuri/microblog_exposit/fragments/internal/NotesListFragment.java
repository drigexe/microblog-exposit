package com.vysocki.yuri.microblog_exposit.fragments.internal;

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

import com.vysocki.yuri.microblog_exposit.MainActivity;
import com.vysocki.yuri.microblog_exposit.Note;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.SharedViewModel;

public class NotesListFragment extends Fragment {

    private SharedViewModel viewModel;

    TextView textView;
    Button button1;
    Button button2;
    String text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);

        textView = view.findViewById(R.id.notetextview);
        button1 = view.findViewById(R.id.buttonresult1);
        button2 = view.findViewById(R.id.buttonresult2);

        text = textView.getText().toString();
        final Note note = new Note();
        note.setNoteText(text);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setNote(note);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Another text");
                text = textView.getText().toString();
                note.setNoteText(text);
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
