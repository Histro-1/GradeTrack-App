package com.example.gradetrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity {

    private RecyclerView courseItems;
    private FloatingActionButton addCourse,cancelBtn;
    public static ArrayList<Course> enrolled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        /*
        Initializing views and displaying course content
         We now need an adapter to display the course arrayList
         */
        initViews();
        displayCourses();

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CourseList.this, "Adding new course...", Toast.LENGTH_SHORT).show();

                openNewCourseActivity();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePageActiviety();
            }
        });
    }

    public static Course getCourse(String courseName) {
        int i = 0;
        while(!enrolled.get(i).getName().equals(courseName) && i+1 <= enrolled.size()) {
            i++;
        }

        if(enrolled.get(i).getName().equals(courseName)) {
            return enrolled.get(i);
        }else {
            return null;
        }
    }
//---------------------------------Button Push-------------------------------------------//
    public void openNewCourseActivity(){
        Intent intent = new Intent(this, New_Course_Activity.class);
        startActivity(intent);
    }
    public void openHomePageActiviety(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
//--------------------------------------------------------------------------------------//
    public void initViews(){
        addCourse = findViewById(R.id.addCourse);
        courseItems = findViewById(R.id.displayCourses);
        cancelBtn = findViewById(R.id.cancelBtn);
        enrolled = DataBase.getInstance().getEnrolled();
    }
    public void displayCourses(){

        CourseAdapter adapter = new CourseAdapter(this);

        adapter.setCourses(enrolled);

        courseItems.setAdapter(adapter);

        courseItems.setLayoutManager(new GridLayoutManager(this,3));
    }
}