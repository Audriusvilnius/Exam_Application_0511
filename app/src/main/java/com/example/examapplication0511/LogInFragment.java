package com.example.examapplication0511;

import static android.view.View.X;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {

    public static LogInFragment newInstance() {
        LogInFragment fragment = new LogInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    private Boolean checkLogIn;
    private Button logInButton;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_log_in, container, false);
        // Inflate the layout for this fragment

        EditText email = view.findViewById(R.id.et_email);
        EditText password = view.findViewById(R.id.et_password);
        logInButton = view.findViewById(R.id.btn_login);
        String hashedPassword = null;
        hashedPassword = RegisterFragment.hashedPassword;
       Log.d("LogInFragment", "Hashed Password: " + hashedPassword);
        logIn(email, password);

        return view;
    }

    private void logIn(EditText userEmail, EditText userPassword) {
        logInButton.setOnClickListener(v -> {
            String email = userEmail.getText().toString();
            String password = userPassword.getText().toString();
            String logInToApp = email + password;
            Log.d("LogInFragment", "Email: " + email + " Password: " + password);

            try {
                // Create MD5 Hash
                MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
                digest.update(logInToApp.getBytes());
                byte[] messageDigest = digest.digest();
                // Create Hex String
                StringBuilder hexString = new StringBuilder();
                for (int i = 0; i < messageDigest.length; i++)
                    hexString.append(String.format("%02X", messageDigest[i]));
                String pass = hexString.toString();
                Log.d("LogInFragment", "Password: " + pass);
            }catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
        });
    }
}


