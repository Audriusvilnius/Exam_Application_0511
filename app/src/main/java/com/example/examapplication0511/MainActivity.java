package com.example.examapplication0511;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        if(LogInFragment.checkLogIn == true) {
            pagerAdapter.addFragmet(new HomeFragment());
            pagerAdapter.addFragmet(new TextFragment());
            pagerAdapter.addFragmet(new ConverterFragment());
        }else{
        pagerAdapter.addFragmet(new LogInFragment());
        pagerAdapter.addFragmet(new RegisterFragment());
        }
//        pagerAdapter.addFragmet(new HomeFragment());
//        pagerAdapter.addFragmet(new TextFragment());
//        pagerAdapter.addFragmet(new ConverterFragment());
        viewPager.setAdapter(pagerAdapter);

    }

    static class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragmet(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }
}