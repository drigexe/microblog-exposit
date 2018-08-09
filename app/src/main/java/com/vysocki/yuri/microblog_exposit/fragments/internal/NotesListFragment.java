package com.vysocki.yuri.microblog_exposit.fragments.internal;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vysocki.yuri.microblog_exposit.MainActivity;
import com.vysocki.yuri.microblog_exposit.MyAdapter;
import com.vysocki.yuri.microblog_exposit.Note;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.SharedViewModel;

import java.util.ArrayList;

public class NotesListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SharedViewModel viewModel;

    Note note = new Note();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);

        String[] myDataset = new String[10];
        myDataset[0] = "kekus";
        myDataset[1] = "LOLOL";
        myDataset[2] = "AZAZAZ";
        myDataset[3] = "AZAZ4325AZ";
        myDataset[4] = "AZA1ZAZ";
        myDataset[5] = "AZA432ZAZ";
        myDataset[6] = "AZA2ZAZ";
        myDataset[7] = "LOLrewtOL";
        myDataset[8] = "LOLweweOL";
        myDataset[9] = "LOL666OL";

        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);



        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setNote(note);
            }
        });*/

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).toggleDrawer(false);
    }

}
