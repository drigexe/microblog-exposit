package com.vysocki.yuri.microblog_exposit.fragments.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vysocki.yuri.microblog_exposit.R;

import androidx.navigation.Navigation;

import static android.support.constraint.Constraints.TAG;

public class SignUpFragment extends Fragment {

    private FirebaseAuth mAuth;

    EditText emailET;
    EditText passwordET;
    EditText passwordConfirmET;
    Button signUpButton;

    String email;
    String password;
    String passwordConfirm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mAuth = FirebaseAuth.getInstance();

        emailET = view.findViewById(R.id.emailRegEditText);
        passwordET = view.findViewById(R.id.passRegEditText);
        passwordConfirmET = view.findViewById(R.id.passConfirmRegEditText);
        signUpButton = view.findViewById(R.id.signUpRegButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromFields();
                if (validation()) {
                    createAccount(email, password, view);
                } else {
                    Toast.makeText(getActivity(), "Fill the fields correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public boolean validation() {
        return password.equals(passwordConfirm);
    }

    public void getValuesFromFields() {
        this.email = emailET.getText().toString();
        this.password = passwordET.getText().toString();
        this.passwordConfirm = passwordConfirmET.getText().toString();
    }

    public void createAccount(String email, String password, final View view) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Navigation.findNavController(view).navigate(R.id.action_signUpExternalFragment_to_notesExternalFragment);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                });

    }
}
