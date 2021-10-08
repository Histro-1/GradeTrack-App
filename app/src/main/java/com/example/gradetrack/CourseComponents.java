package com.example.gradetrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CourseComponents extends AppCompatActivity {

    private TextView courseName, txtExplanation;
    private RecyclerView components;
    private FloatingActionButton addComponentBtn,cancelBtn;
    private ArrayList<Component>  assessments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_components);
        // initialize variables
        initViews();


//-----------------------------------Adapter------------------------------------------
        ComponentAdapter adapter = new ComponentAdapter(this, courseName.getText().toString());
        adapter.setComponents(assessments);
        components.setAdapter(adapter);
        components.setLayoutManager(new GridLayoutManager(this,3));

//-----------------------------------Button push---------------------------------------
        addComponentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    public void initViews() {
        components = findViewById(R.id.componentScores);
        courseName = findViewById(R.id.courseName);
        addComponentBtn = findViewById(R.id.addComponentBtn);
        txtExplanation = findViewById(R.id.txtExplanation);
        cancelBtn = findViewById(R.id.cancelBtn);


        // Get course name
        String name = getIntent().getStringExtra("CourseName");
        courseName.setText(name);
       // courseName_component.setText(name); // this view is for every course component (it holds information about the course they belong to)
        assessments = DataBase.getInstance().getCourse(courseName.getText().toString()).getAssessments();


        if(assessments.size() >= 1){
            double currAverage = DataBase.getInstance().getCourse(courseName.getText().toString()).getCurrentAverage();
            txtExplanation.setText("Current Average" + " - " + Math.round(currAverage));
        }else{
            txtExplanation.setText("Add a component");
        }


    }

    public void openAddComponentActivity(){
        Intent intent = new Intent(this,AddComponentActivity.class);
        intent.putExtra("CourseName", courseName.getText().toString());
        startActivity(intent);
    }
    public void openCourseListActivity(){
        Intent intent = new Intent(this,CourseList.class);
        startActivity(intent);
    }
}




