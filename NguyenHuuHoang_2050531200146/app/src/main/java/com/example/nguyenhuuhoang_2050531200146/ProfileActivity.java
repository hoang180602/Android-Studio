package com.example.nguyenhuuhoang_2050531200146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private Button btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnLogOut = findViewById(R.id.btnLogOut);
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_List:
                        Toast.makeText(ProfileActivity.this, "Trò chơi", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.action_Profile:
                        Toast.makeText(ProfileActivity.this, "Hồ sơ ", Toast.LENGTH_SHORT).show();

                        break;
                }
                return true;
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}