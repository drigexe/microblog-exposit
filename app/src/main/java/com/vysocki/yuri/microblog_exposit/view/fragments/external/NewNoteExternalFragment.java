package com.vysocki.yuri.microblog_exposit.view.fragments.external;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.view.fragments.internal.NewNoteFragment;

public class NewNoteExternalFragment extends ExternalFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_external, container, false);

        NewNoteFragment newNoteFragment = new NewNoteFragment();
        setInternalFragment(R.id.fragment_container, newNoteFragment);

        return view;
    }
}
