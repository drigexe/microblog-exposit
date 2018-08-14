package com.vysocki.yuri.microblog_exposit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vysocki.yuri.microblog_exposit.R;

import java.util.ArrayList;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "NoteRecyclerViewAdapter";

    private ArrayList<String> mNoteThemes = new ArrayList<>();
    private ArrayList<String> mNoteDates = new ArrayList<>();
    private Context mContext;

    public NotesRecyclerViewAdapter(Context mContext, ArrayList<String> mNoteThemes, ArrayList<String> mNoteDates) {
        this.mNoteThemes = mNoteThemes;
        this.mNoteDates = mNoteDates;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.themeTextView.setText(mNoteThemes.get(position));
        holder.dateTextView.setText(mNoteDates.get(position));

    }

    @Override
    public int getItemCount() {
        return mNoteThemes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView themeTextView;
        TextView dateTextView;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            themeTextView = itemView.findViewById(R.id.noteThemeText);
            dateTextView = itemView.findViewById(R.id.noteDateText);
            parentLayout = itemView.findViewById(R.id.recycler_parent_layout);
        }
    }
}
