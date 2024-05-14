package com.example.examapplication0511;

import static android.view.View.X;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    public static Boolean checkLogIn = false;
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
        TextView message = view.findViewById(R.id.login_message);
        message.setVisibility(View.INVISIBLE);
        logInButton = view.findViewById(R.id.btn_login);
        logIn(email, password);
        return view;
    }

    @SuppressLint("SetTextI18n")
    private void logIn(EditText userEmail, EditText userPassword) {
        logInButton.setOnClickListener(v -> {
            String email = userEmail.getText().toString();
            String password = userPassword.getText().toString();
            Activity view = getActivity();
            assert view != null;
            TextView message = view.findViewById(R.id.login_message);
            String checkLogIn = null;
            checkLogIn = RegisterFragment.passwordHash(userPassword, userEmail);
            String hashedPassword = null;
            hashedPassword = RegisterFragment.hashedPassword;
            boolean checkPass = false;
            if (hashedPassword != null) {
                checkPass = hashedPassword.equals(checkLogIn);
                if (checkPass) {
                    message.setTextColor(getResources().getColor(R.color.green));
                    message.setText("Log In Successful");
                    message.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(() -> {
                        message.setVisibility(View.INVISIBLE);
                    }, 3000);
                    Log.d("LogInFragment", "Log In Successful: "+ checkLogIn);
                    openHome();

                } else {
                    message.setTextColor(getResources().getColor(R.color.red));
                    message.setText("Incorrect Username or password");
                    message.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(() -> {
                        message.setVisibility(View.INVISIBLE);
                    }, 3000);
                }
            } else {
                message.setTextColor(getResources().getColor(R.color.red));
                message.setText("User not found");
                message.setVisibility(View.VISIBLE);
                new Handler().postDelayed(() -> {
                    message.setVisibility(View.INVISIBLE);
                }, 3000);
            }
        });
    }

    public void openHome() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        checkLogIn = true;
    }
}


