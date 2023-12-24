package dev.mobile.showroom;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
public class Admin extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bt_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigListener);
    }

    @SuppressLint("NonConstantResourceId")
    private BottomNavigationView.OnNavigationItemSelectedListener navigListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    int itemId = item.getItemId(); // Store the itemId to avoid multiple calls

                    if (itemId == R.id.addfrag) {
                        selectedFragment = new add();
                    }

                    if (itemId == R.id.update) {
                        selectedFragment = new update();
                    }

                    if (itemId == R.id.delete) {
                        selectedFragment = new deleteFragment();
                    }

                    if (selectedFragment != null) {
                        String URL = "http://192.168.1.14:80";
                        Bundle bundle = new Bundle();
                        bundle.putString("url", URL);
                        selectedFragment.setArguments(bundle);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.addplaceholer, selectedFragment)
                                .commit();
                    }

                    return true;
                }
            };
}
