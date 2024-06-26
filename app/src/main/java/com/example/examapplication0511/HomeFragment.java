package com.example.examapplication0511;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final int[] welcome_Pic = {
            R.drawable.designer,
            R.drawable.designer1,
            R.drawable.designer2,
            R.drawable.designer3,
            R.drawable.designer4,
            R.drawable.designer5,
    };
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        setWelcomePic(imageView);
        Button button = view.findViewById(R.id.btn_logout_home);
        logOutButton(button);
        // Inflate the layout for this fragment
        return view;
    }

    public void logOutButton(Button button) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            LogInFragment.checkLogIn = false;
        });
    }

    private void setWelcomePic(ImageView imageView) {
        imageView.setImageResource(welcome_Random());
        imageView.setOnClickListener(v -> {
            imageView.setImageResource(welcome_Random());
            imageView.setOnClickListener(v1 -> {
                setWelcomePic(imageView);
            });
        });
    }

    private int welcome_Random() {
        Random rand = new Random();
        return welcome_Pic[rand.nextInt(welcome_Pic.length)];
    }
}