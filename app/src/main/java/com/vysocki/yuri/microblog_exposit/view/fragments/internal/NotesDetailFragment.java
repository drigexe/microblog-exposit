package com.vysocki.yuri.microblog_exposit.view.fragments.internal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vysocki.yuri.microblog_exposit.view.activities.MainActivity;
import com.vysocki.yuri.microblog_exposit.model.Note;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.viewmodel.SharedViewModel;

public class NotesDetailFragment extends Fragment {

    SharedViewModel viewModel;

    TextView theme;
    TextView themeTitle;
    TextView text;
    TextView textTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_detail, container, false);

        theme = view.findViewById(R.id.themeDetailText);
        themeTitle = view.findViewById(R.id.themeDetailTitle);
        text = view.findViewById(R.id.textDetailText);
        textTitle = view.findViewById(R.id.textDetailTitle);

        if (viewModel.getNote().getValue() == null) {
            themeTitle.setVisibility(View.INVISIBLE);
            textTitle.setVisibility(View.INVISIBLE);
        }

        Observer<Note> noteObserver = new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                if (viewModel.getNote().getValue() != null) {
                    //when note is set
                    themeTitle.setVisibility(View.VISIBLE);
                    textTitle.setVisibility(View.VISIBLE);
                    theme.setText(note.getTheme());
                    text.setText(note.getText());
                } else {
                    // when note is cleared
                    Toast.makeText(getActivity(),"An error has occurred", Toast.LENGTH_LONG).show();
                }
            }
        };

        viewModel.getNote().observe(this, noteObserver);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).toggleDrawer(false);
    }

}
