package com.example.random;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BMW_infomation extends AppCompatActivity {

    TextView myTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmw_infomation);

        myTextView = (TextView) findViewById(R.id.textGet);

        Intent a = getIntent();
        String value1 = a.getStringExtra("Key_1");

        myTextView.setText(value1);

    }
}