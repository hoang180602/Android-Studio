package com.example.tinhchisobmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button add_button;
    private EditText weight, height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float value_1 = Float.parseFloat(weight.getText().toString());
                float value_2 = Float.parseFloat(height.getText().toString());
                float sum = value_1/(value_2*value_2);

                if(sum < 18)
                    Toast.makeText(getApplicationContext(), "BMI < 18: Người gầy", Toast.LENGTH_SHORT).show();
                else if(sum >= 18 && sum <=24.9)
                    Toast.makeText(getApplicationContext(), "BMI = 18 – 24,9: người bình thường", Toast.LENGTH_SHORT).show();
                else if(sum >= 25 && sum <=29.9)
                    Toast.makeText(getApplicationContext(), "BMI = 25 – 29,9: người béo phì độ I", Toast.LENGTH_SHORT).show();
                else if(sum >= 30 && sum <=34.9)
                    Toast.makeText(getApplicationContext(), "BMI = 30 – 34,9: người béo phì độ II", Toast.LENGTH_SHORT).show();
                else if(sum >35)
                    Toast.makeText(getApplicationContext(), "BMI > 35: người béo phì độ III", Toast.LENGTH_SHORT).show();
            }
        });

    }
}