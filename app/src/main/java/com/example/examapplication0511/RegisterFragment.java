package com.example.examapplication0511;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public String name;
    public static String hashedPassword;
    private Button registerButton;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_registe, container, false);
        // Inflate the layout for this fragment
        EditText name = view.findViewById(R.id.et_name);
        EditText email = view.findViewById(R.id.et_email);
        EditText password = view.findViewById(R.id.et_password);
        EditText confirmPassword = view.findViewById(R.id.et_repassword);
        TextView message = view.findViewById(R.id.reg_message);
        message.setVisibility(View.INVISIBLE);
        registerButton = view.findViewById(R.id.btn_register);
        // Register the user
        registerUser(name, email, password, confirmPassword);

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void registerUser(EditText newName, EditText newEmail, EditText newPassword, EditText newConfirmPassword) {
        registerButton.setOnClickListener(v -> {
            String name = newName.getText().toString();
            String email = newEmail.getText().toString();
            String password = newPassword.getText().toString();
            String confirmPassword = newConfirmPassword.getText().toString();

            if (password.equals(confirmPassword) && !password.isEmpty()) {
                if (name.isEmpty() || email.isEmpty()) {
                    Activity view = null;
                    view = getActivity();
                    assert view != null;
                    TextView message = view.findViewById(R.id.reg_message);
                    message.setVisibility(View.VISIBLE);
                    message.setText("Please fill in all fields");
                    new Handler().postDelayed(() -> {
                        message.setVisibility(View.INVISIBLE);
                    }, 3000);
                } else {
                    // Hash the password
                    hashedPassword = passwordHash(newPassword, newEmail);
                    name = newName.getText().toString();
                    // Save the user to the database
                    Activity view = getActivity();
                    assert view != null;
                    TextView message = view.findViewById(R.id.reg_message);
                    // Display a message to the user
                    message.setVisibility(View.VISIBLE);
                    message.setTextColor(getResources().getColor(R.color.green));
                    message.setText("Registration Successful");
                    new Handler().postDelayed(() -> {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }, 3000);
                }
            } else {
                Activity view_pass = getActivity();
                assert view_pass != null;
                TextView message_pass = view_pass.findViewById(R.id.reg_message);
                message_pass.setVisibility(View.VISIBLE);
                message_pass.setText("Password and Confirm Password are not the same");
                new Handler().postDelayed(() -> {
                    message_pass.setVisibility(View.INVISIBLE);
                }, 3000);
            }
        });
    }

    // Hash the password
    private String passwordHash(EditText newPassword, EditText newSalt) {
        String password = newPassword.getText().toString();
        String salt = newSalt.getText().toString();
        password = password + salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            Log.d("RegisterFragment", "Hashed Password: " + hashedPassword);
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

}