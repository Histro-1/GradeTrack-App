package com.example.gradetrack;

import static com.example.gradetrack.CourseList.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class New_Course_Activity extends AppCompatActivity {

    ArrayList<Course> current = enrolled;
    private FloatingActionButton sendCourseBtn,cancelBtn;
    private EditText edtCourseName;
    private String courseName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        initViews();

        sendCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseName = edtCourseName.getText().toString();
                current.add(new Course(courseName));

                 openAddComponentActivity();

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCourseListActivity();

            }
        });

    }

    public void initViews(){
        sendCourseBtn = findViewById(R.id.sendCourseBtn);
        edtCourseName = findViewById(R.id.edtCourseName);
        cancelBtn = findViewById(R.id.cancelBtn);
    }

    public void  openCourseListActivity(){
        Intent intent = new Intent(this, CourseList.class);
        startActivity(intent);
    }
    public void openAddComponentActivity(){
        Intent intent = new Intent(this,AddComponentActivity.class);
        intent.putExtra("CourseName",courseName);
        startActivity(intent);
    }
}