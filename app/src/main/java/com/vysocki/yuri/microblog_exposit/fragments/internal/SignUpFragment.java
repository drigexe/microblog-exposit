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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vysocki.yuri.microblog_exposit.R;
import com.vysocki.yuri.microblog_exposit.User;

import androidx.navigation.Navigation;

import static android.support.constraint.Constraints.TAG;

public class SignUpFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    EditText emailET;
    EditText passwordET;
    EditText passwordConfirmET;
    EditText firstNameET;
    EditText lastNameET;
    EditText ageET;
    Button signUpButton;
    RadioGroup genderRadio;

    String email;
    String password;
    String passwordConfirm;
    String firstName;
    String lastName;
    String age;
    String gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("users");

        emailET = view.findViewById(R.id.emailRegEditText);
        passwordET = view.findViewById(R.id.passRegEditText);
        passwordConfirmET = view.findViewById(R.id.passConfirmRegEditText);
        firstNameET = view.findViewById(R.id.firstNameEditText);
        lastNameET = view.findViewById(R.id.lastNameEditText);
        ageET = view.findViewById(R.id.ageEditText);
        signUpButton = view.findViewById(R.id.signUpRegButton);
        genderRadio = view.findViewById(R.id.genderRadio);

        gender = "male";
        genderRadio.setOnCheckedChangeListener(onCheckedChangeListener);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromFields();
                if (validation()) {
                    User user = new User(age, email, firstName, gender, lastName);
                    createAccount(email, password, user, view);
                } else {
                    Toast.makeText(getActivity(), "Fill the fields correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public boolean validation() {
        return (password.equals(passwordConfirm) &&
                (!password.equals("")) && (!firstName.equals(""))
                 && (!lastName.equals("")) && (!age.equals(""))
                 && (!email.equals("")));
    }

    public void getValuesFromFields() {
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        passwordConfirm = passwordConfirmET.getText().toString();
        firstName = firstNameET.getText().toString();
        lastName = lastNameET.getText().toString();
        age = ageET.getText().toString();
    }

    public void createAccount(String email, String password, final User user, final View view) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String uId = firebaseUser.getUid();
                            mRef.child(uId).setValue(user);
                            Navigation.findNavController(view).navigate(R.id.action_signUpExternalFragment_to_notesExternalFragment);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio_male:
                    gender = "male";
                    break;
                case R.id.radio_female:
                    gender = "female";
                    break;
            }
        }
    };
}
