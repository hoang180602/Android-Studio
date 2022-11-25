package com.example.nguyenhuuhoang_2050531200146;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseRVAdapter.CourseClickInterface{


    private RecyclerView rvCourses;
    private ProgressBar pbLoading;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<CourseRVModal> courseRVModalArrayList;
    private RelativeLayout bottomSheetRL;
    private CourseRVAdapter courseRVAdapter;
    private CourseRVModal courseRVModal;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        rvCourses = findViewById(R.id.rvCourses);
        pbLoading = findViewById(R.id.pbLoading);
        addFAB = findViewById(R.id.btnAdd);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Courses");
        courseRVModalArrayList = new ArrayList<>();
        bottomSheetRL = findViewById(R.id.rlbSheet);
        courseRVModal = getIntent().getParcelableExtra("course");
        courseRVAdapter = new CourseRVAdapter(courseRVModalArrayList,this,this);
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(courseRVAdapter);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_Profile:
                        Toast.makeText(MainActivity.this, "Hồ sơ ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(i);
                        break;
                    case R.id.action_List:
                        Toast.makeText(MainActivity.this, "Trò chơi", Toast.LENGTH_SHORT).show();
                        break;

                }
                    return true;
            }
        });

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddCourseActivity.class));
            }
        });

        getAllCourses();
    }

    private void getAllCourses(){
        courseRVModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                pbLoading.setVisibility(View.GONE);
                courseRVModalArrayList.add(snapshot.getValue(CourseRVModal.class));
                courseRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                pbLoading.setVisibility(View.GONE);
                courseRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                pbLoading.setVisibility(View.GONE);
                courseRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                pbLoading.setVisibility(View.GONE);
                courseRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCourseClick(int position) {
        displayBottomSheet(courseRVModalArrayList.get(position));
    }
    private void displayBottomSheet(CourseRVModal courseRVModal){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView tvCourseName = layout.findViewById(R.id.tvCourseName);
        TextView tvDescription = layout.findViewById(R.id.tvDescription);
        TextView tvSuitedFor = layout.findViewById(R.id.tvSuitedFor);
        TextView tvPrice = layout.findViewById(R.id.tvPrice);
        ImageView ivCourse = layout.findViewById(R.id.ivCourse);
        Button btnEditCourse = layout.findViewById(R.id.btnEditCourse);


        tvCourseName.setText("Tên trò chơi: "+courseRVModal.getCourseName());
        tvDescription.setText("Mô tả: "+courseRVModal.getCourseDescription());
        tvSuitedFor.setText("Đối tượng: "+ courseRVModal.getCourseSuitedFor());
        tvPrice.setText(courseRVModal.getCoursePrice());
        Picasso.get().load(courseRVModal.getCourseImage()).into(ivCourse);

        btnEditCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EditCourseActivity.class);
                i.putExtra("course",courseRVModal);
                startActivity(i);
            }
        });



    }

}