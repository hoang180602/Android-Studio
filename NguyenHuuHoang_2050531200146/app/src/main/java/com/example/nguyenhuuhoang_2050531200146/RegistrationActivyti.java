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

public class RegistrationActivyti extends AppCompatActivity {

    private TextInputEditText edtUserName, edtPassword, edtConfirmPassword;
    private Button btnResgister;
    private ProgressBar pbLoading;
    private TextView tvLogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activyti);

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnResgister = findViewById(R.id.btnResgister);
        pbLoading = findViewById(R.id.pbLoading);
        tvLogin = findViewById(R.id.tvLogin);
        mAuth = FirebaseAuth.getInstance();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivyti.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnResgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbLoading.setVisibility(View.VISIBLE);
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();

                if(!password.equals(confirmPassword)){
                    Toast.makeText(RegistrationActivyti.this, "Không trùng với mật khẩu!", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(RegistrationActivyti.this, "Xin vui lòng nhập đẩy đủ thông tin!",Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 pbLoading.setVisibility(View.GONE);
                                 Toast.makeText(RegistrationActivyti.this,"Đăng kí thành công!",Toast.LENGTH_SHORT).show();
                                 Intent i = new Intent(RegistrationActivyti.this, LoginActivity.class);
                                 startActivity(i);
                                 finish();
                             }else{
                                 pbLoading.setVisibility(View.GONE);
                                 Toast.makeText(RegistrationActivyti.this,"Tài khoản là 1 gmail\nMật khẩu >=6 kí tự",Toast.LENGTH_SHORT).show();
                             }
                        }
                    });
                }
            }
        });
    }
}