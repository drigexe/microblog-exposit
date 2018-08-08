package com.vysocki.yuri.microblog_exposit.fragments.external;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.fragments.external.ExternalFragment;
import com.vysocki.yuri.microblog_exposit.fragments.internal.NotesDetailFragment;
import com.vysocki.yuri.microblog_exposit.fragments.internal.NotesListFragment;

public class NotesExternalFragment extends ExternalFragment {

    FragmentTransaction transaction;
    private ViewGroup secondContainerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_external, container, false);

        //load the NotesListFragment
        NotesListFragment notesListFragment = new NotesListFragment();
        setInternalFragment(R.id.fragment_container, notesListFragment, transaction);

        //if device is in landscape orientation then load the NotesDetailFragment
        secondContainerLayout = view.findViewById(R.id.fragment_second_container);
        if (secondContainerLayout != null) {

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1f
            );

            secondContainerLayout.setLayoutParams(param);

            NotesDetailFragment notesDetailFragment = new NotesDetailFragment();
            setInternalFragment(R.id.fragment_second_container, notesDetailFragment, transaction);
        }

        return view;
    }

}
