package com.vysocki.yuri.microblog_exposit.fragments.external;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vysocki.yuri.microblog_exposit.MainActivity;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.fragments.internal.NotesDetailFragment;

public class NotesDetailExternalFragment extends ExternalFragment {

    FragmentTransaction transaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotesDetailFragment notesDetailFragment = new NotesDetailFragment();
        setInternalFragment(R.id.fragment_container, notesDetailFragment, transaction);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_external, container, false);

        return view;
    }
}
