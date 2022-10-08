package com.example.testnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signInAcitivy extends AppCompatActivity {

    Button btnSignUp, btnSignIn;
    EditText editTextUser, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_acitivy);

        btnSignUp = (Button) findViewById(R.id.buttonSignUp);
        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent a = new Intent(signInAcitivy.this, signUpActivity.class);
                startActivity(a);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(editTextUser.getText().toString().equals("admin") && editTextPassword.getText().toString().equals("admin")){
                    Intent a = new Intent(signInAcitivy.this, MainActivity.class);
                    startActivity(a);
                    Toast.makeText(signInAcitivy.this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(signInAcitivy.this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_LONG).show();
            }
        });
    }
}