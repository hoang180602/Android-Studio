package com.example.nguyenhuuhoang_2050531200146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtUserName, edtPassword;
    private TextView tvRegister;
    private Button btnLogin;
    private ProgressBar pbLoading;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        tvRegister = findViewById(R.id.tvRegister);
        btnLogin = findViewById(R.id.btnLogin);
        pbLoading = findViewById(R.id.pbLoading);
        mAuth = FirebaseAuth.getInstance();

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivyti.class);
                startActivity(i);
            }
        });

         btnLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 pbLoading.setVisibility(View.VISIBLE);
                 String userName =  edtUserName.getText().toString();
                 String password = edtPassword.getText().toString();
                 if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)){
                     Toast.makeText(LoginActivity.this,"Vùi lòng nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                     return;
                 }else{
                     mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 pbLoading.setVisibility(View.GONE);
                                 Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                 Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                                 startActivity(i);
                                 finish();
                             }else{
                                 pbLoading.setVisibility(View.GONE);
                                 Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
                 }
             }
         });
    }



}