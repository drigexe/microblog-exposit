package com.vysocki.yuri.microblog_exposit.view.fragments.internal;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vysocki.yuri.microblog_exposit.view.activities.MainActivity;
import com.vysocki.yuri.microblog_exposit.model.Note;
import com.vysocki.yuri.microblog_exposit.R;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import androidx.navigation.Navigation;

public class NewNoteFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    EditText theme;
    EditText text;
    Button saveButton;

    String themeValue;
    String textValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_note, container, false);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("notes");

        theme = view.findViewById(R.id.themeNewNoteEditText);
        text = view.findViewById(R.id.textNewNoteEditText);
        saveButton = view.findViewById(R.id.saveNewNoteButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromFields();
                if (validation()) {
                    String uniqueID = UUID.randomUUID().toString();
                    Date currentDate = Calendar.getInstance().getTime();
                    Note note = new Note(themeValue, textValue, currentDate);
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String uId = firebaseUser.getUid();
                    mRef.child(uId).child(uniqueID).setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            theme.setText(null);
                            text.setText(null);
                            Toast.makeText(getActivity(),"Note created!", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(getView()).navigate(R.id.action_newNoteExternalFragment_to_notesExternalFragment);
                        }
                    });
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).toggleDrawer(false);
    }

    public boolean validation() {
        return ((!themeValue.equals("")) && (!textValue.equals("")));
    }

    public void getValuesFromFields() {
        themeValue = theme.getText().toString();
        textValue = text.getText().toString();
    }

}