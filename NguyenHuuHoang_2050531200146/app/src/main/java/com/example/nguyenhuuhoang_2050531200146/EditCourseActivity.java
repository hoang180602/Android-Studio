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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditCourseActivity extends AppCompatActivity {

    private TextInputEditText edtCourseName, edtCoursePrice, edtCourseSuitedFor, edtCourseImageLink, edtCourseLink, edtCourseDesc;
    private Button btnUpdateCourse, btnDeleteCourse;
    private ProgressBar pbLoading;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String courseID;
    private CourseRVModal courseRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        edtCourseName = findViewById(R.id.edtCourseName);
        edtCoursePrice = findViewById(R.id.edtCoursePrice);
        edtCourseSuitedFor = findViewById(R.id.edtCourseStuitedFor);
        edtCourseImageLink = findViewById(R.id.edtCourseImageLink);
        edtCourseLink = findViewById(R.id.edtCourseLink);
        edtCourseDesc = findViewById(R.id.edtCourseDesc);
        btnUpdateCourse = findViewById(R.id.btnUpdateCourse);
        btnDeleteCourse = findViewById(R.id.btnDeleteCourse);
        pbLoading = findViewById(R.id.pbLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        courseRVModal = getIntent().getParcelableExtra("course");
        if(courseRVModal!=null){
            edtCourseName.setText(courseRVModal.getCourseName());
            edtCourseLink.setText(courseRVModal.getCourseLink());
            edtCourseDesc.setText(courseRVModal.getCourseDescription());
            edtCourseImageLink.setText(courseRVModal.getCourseImage());
            edtCourseSuitedFor.setText(courseRVModal.getCourseSuitedFor());
            edtCoursePrice.setText(courseRVModal.getCoursePrice());
            courseID = courseRVModal.getCourseID();
        }


        databaseReference = firebaseDatabase.getReference("Courses").child(courseID);

        btnUpdateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbLoading.setVisibility(View.VISIBLE);
                String courseName = edtCourseName.getText().toString();
                String courseSuitedFor = edtCourseSuitedFor.getText().toString();
                String courseImageLink = edtCourseImageLink.getText().toString();
                String  courseLink = edtCourseLink.getText().toString();
                String courseDesc = edtCourseDesc.getText().toString();
                String coursePrice = edtCoursePrice.getText().toString();


                Map<String, Object> map = new HashMap<>();
                map.put("courseName",courseName);
                map.put("courseDescription",courseDesc);
                map.put("coursePrice",coursePrice);
                map.put("courseSuitedFor",courseSuitedFor);
                map.put("courseImage",courseImageLink);
                map.put("courseLink",courseLink);
                map.put("courseID",courseID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        pbLoading.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditCourseActivity.this,"Cập nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditCourseActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditCourseActivity.this,"Cập nhập thất bại",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        btnDeleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse();
            }
        });
    }

    private void deleteCourse(){
        databaseReference.removeValue();
        Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditCourseActivity.this,MainActivity.class));

    }
}