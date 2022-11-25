package com.example.nguyenhuuhoang_2050531200146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCourseActivity extends AppCompatActivity {
    private TextInputEditText edtCourseName, edtCoursePrice, edtCourseSuitedFor, edtCourseImageLink, edtCourseLink, edtCourseDesc;
    private Button btnAddCourse;
    private ProgressBar pbLoading;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        edtCourseName = findViewById(R.id.edtCourseName);
        edtCoursePrice = findViewById(R.id.edtCoursePrice);
        edtCourseSuitedFor = findViewById(R.id.edtCourseStuitedFor);
        edtCourseImageLink = findViewById(R.id.edtCourseImageLink);
        edtCourseLink = findViewById(R.id.edtCourseLink);
        edtCourseDesc = findViewById(R.id.edtCourseDesc);
        btnAddCourse = findViewById(R.id.btnAddCourse);
        pbLoading = findViewById(R.id.pbLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Courses");

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbLoading.setVisibility(View.VISIBLE);
                String courseName = edtCourseName.getText().toString();
                String courseSuitedFor = edtCourseSuitedFor.getText().toString();
                String courseImageLink = edtCourseImageLink.getText().toString();
                String  courseLink = edtCourseLink.getText().toString();
                String courseDesc = edtCourseDesc.getText().toString();
                String coursePrice = edtCoursePrice.getText().toString();
                courseID = courseName;
                CourseRVModal courseRVModal = new CourseRVModal(courseName,courseDesc,coursePrice,courseSuitedFor,courseImageLink,courseLink,courseID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        pbLoading.setVisibility(View.GONE);
                        databaseReference.child(courseID).setValue(courseRVModal);
                        Toast.makeText(AddCourseActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddCourseActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddCourseActivity.this,"Lỗi"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}