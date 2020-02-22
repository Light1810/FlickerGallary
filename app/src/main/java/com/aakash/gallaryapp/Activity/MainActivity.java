package com.aakash.gallaryapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;

import com.aakash.gallaryapp.Adapter.DashboardViewPagerAdapter;
import com.aakash.gallaryapp.Fragment.HomeFragment;
import com.aakash.gallaryapp.Fragment.SearchFragment;
import com.aakash.gallaryapp.Model.FlickerResponse;
import com.aakash.gallaryapp.Model.Photo;
import com.aakash.gallaryapp.Model.Photos;
import com.aakash.gallaryapp.R;
import com.aakash.gallaryapp.ViewModel.FlickerViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    ViewPager viewPager;
    DashboardViewPagerAdapter dashboardViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.viewPager);



        dashboardViewPagerAdapter = new DashboardViewPagerAdapter(getSupportFragmentManager());
        dashboardViewPagerAdapter.addFragment(new HomeFragment());
        dashboardViewPagerAdapter.addFragment(new SearchFragment());






        viewPager.setAdapter(dashboardViewPagerAdapter);

        bottomNavigation.setSelectedItemId(R.id.nav_home);
        viewPager.setCurrentItem(0);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.nav_search:
                        viewPager.setCurrentItem(1);
                        return true;

                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 1)
                    bottomNavigation.setSelectedItemId(R.id.nav_search);

                if (position == 0)
                    bottomNavigation.setSelectedItemId(R.id.nav_home);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }

}
