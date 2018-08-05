package com.vysocki.yuri.microblog_exposit.fragments.external;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.fragments.external.ExternalFragment;
import com.vysocki.yuri.microblog_exposit.fragments.internal.NotesDetailFragment;
import com.vysocki.yuri.microblog_exposit.fragments.internal.NotesListFragment;

public class NotesExternalFragment extends ExternalFragment {

    FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_external, container, false);

        NotesListFragment notesListFragment = new NotesListFragment();

        setInternalFragment(R.id.fragment_container, notesListFragment, transaction);

        return view;
    }
}
