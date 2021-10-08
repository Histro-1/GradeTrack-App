package com.example.gradetrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    private TextView txtCurrentAverage, txtCourseEnrolled;
    private ImageView imgCurrentAvg, imgEnrolledCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initValues(); // initialize views...

        txtCourseEnrolled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCourseListActivity();
            }
        });

        imgEnrolledCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCourseListActivity();
            }
        });

        txtCurrentAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCurrentAverageActivity();
            }
        });

        imgCurrentAvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCurrentAverageActivity();
            }
        });


    }

    public void initValues(){
        txtCurrentAverage = findViewById(R.id.txtCurrentAverage);
        txtCourseEnrolled = findViewById(R.id.txtCourseEnrolled);
        imgCurrentAvg = findViewById(R.id.imgCurrentAvg);
        imgEnrolledCourse = findViewById(R.id.imgEnrolledCourse);

    }
    public void openCourseListActivity(){
        Intent intent = new Intent(HomePage.this, CourseList.class);
        startActivity(intent);
    }
    public void openCurrentAverageActivity(){
        Intent intent = new Intent(HomePage.this, CurrentAverageActivity.class);
        startActivity(intent);
    }
}