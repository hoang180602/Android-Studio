package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myTextView = (TextView) findViewById(R.id.textView);

        Intent a = getIntent();
        String value1 = a.getStringExtra("Key_1");

        myTextView.setText("Màn hình chi tiết : "+value1);


    }
}