package com.vysocki.yuri.microblog_exposit.fragments.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vysocki.yuri.microblog_exposit.MainActivity;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.User;

public class UserProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    TextView email;
    TextView firstname;
    TextView lastname;
    TextView gender;
    TextView age;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String uId = firebaseUser.getUid();
        mRef = FirebaseDatabase.getInstance().getReference().child("users").child(uId);

        email = view.findViewById(R.id.userEmailText);
        firstname = view.findViewById(R.id.userFirstnameText);
        lastname = view.findViewById(R.id.userLastnameText);
        gender = view.findViewById(R.id.userGenderText);
        age= view.findViewById(R.id.userAgeText);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).toggleDrawer(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mRef.addValueEventListener(userListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (userListener != null) {
            mRef.removeEventListener(userListener);
        }
    }

    ValueEventListener userListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            User user = dataSnapshot.getValue(User.class);
            email.setText(user.getEmail());
            firstname.setText(user.getFirstname());
            lastname.setText(user.getLastname());
            gender.setText(user.getGender());
            age.setText(user.getAge());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
