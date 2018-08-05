package com.vysocki.yuri.microblog_exposit.fragments.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vysocki.yuri.microblog_exposit.MainActivity;
import com.vysocki.yuri.microblog_exposit.R;

import androidx.navigation.Navigation;

public class LoginScreenFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_screen, container, false);

        ((MainActivity)getActivity()).toggleDrawer(true);

        Button button = view.findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginScreenExternalFragment_to_notesExternalFragment);
            }
        });

        return view;
    }
}
