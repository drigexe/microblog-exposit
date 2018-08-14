package com.vysocki.yuri.microblog_exposit.view.fragments.external;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.view.fragments.internal.NotesDetailFragment;
import com.vysocki.yuri.microblog_exposit.view.fragments.internal.NotesListFragment;

import androidx.navigation.Navigation;

public class NotesExternalFragment extends ExternalFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_external, container, false);

        setHasOptionsMenu(true);

        //load the NotesListFragment
        NotesListFragment notesListFragment = new NotesListFragment();
        setInternalFragment(R.id.fragment_container, notesListFragment);

        //if device is in landscape orientation then load the NotesDetailFragment
        ViewGroup secondContainerLayout;
        secondContainerLayout = view.findViewById(R.id.fragment_second_container);
        if (secondContainerLayout != null) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    0.7f
            );
            secondContainerLayout.setLayoutParams(param);
            NotesDetailFragment notesDetailFragment = new NotesDetailFragment();
            setInternalFragment(R.id.fragment_second_container, notesDetailFragment);
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_add_note) {
            Navigation.findNavController(getView()).navigate(R.id.action_notesExternalFragment_to_newNoteExternalFragment);
        }
        return super.onOptionsItemSelected(item);
    }
}
