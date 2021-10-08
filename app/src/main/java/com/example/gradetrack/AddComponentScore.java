package com.example.gradetrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddComponentScore extends AppCompatActivity {

    private FloatingActionButton sendScoreBtn, cancelBtn;
    private TextView courseName, componentName;
    private EditText edtScore, edtWeight;
    private ImageView imgAddBtn, imgRemoveBtn;
    private ArrayList<Integer> scores = new ArrayList<>();
    private RecyclerView componentScores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component_score);

        initViews();


//------------------------------------------Adapter------------------------------------------------//

        AddComponentAdapter adapter = new AddComponentAdapter(this, componentName.getText().toString());
        adapter.setComponentScores(scores);
        componentScores.setAdapter(adapter);
        componentScores.setLayoutManager(new LinearLayoutManager(this));


//--------------------------------------Buttons----------------------------------------------------//

        imgAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO add a score to the componemtScores array from the edtScore input sub component.
              //  Toast.makeText(AddComponentScore.this, "Hello World!", Toast.LENGTH_SHORT).show();
                int score = Integer.parseInt(edtScore.getText().toString());
                scores.add(score);
                // update adapter...
                adapter.setComponentScores(scores);
            }
        });

        sendScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO suppose to implement the changes for the the scores list.
                //replace scores in database with these set of scores.
                updateScores();
                openCourseComponentsActivity();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCourseComponentsActivity();
            }
        });
    }

    public void initViews(){
        sendScoreBtn = findViewById(R.id.sendScoreBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        courseName = findViewById(R.id.courseName);
        componentName = findViewById(R.id.componentName);
        componentScores = findViewById(R.id.componentScores);
        imgAddBtn = findViewById(R.id.imgAddBtn);
        edtScore = findViewById(R.id.edtScore);
        componentName.setText( getIntent().getStringExtra("ComponentName"));
        courseName.setText( getIntent().getStringExtra("CourseName"));

        copyDatabaseScores();
    }


    public void copyDatabaseScores(){
        // copy scores from data base
        ArrayList<Integer> scoreFromDatabase = DataBase.getInstance().getComponentScores(courseName.getText().toString(),componentName.getText().toString());

        for(int i = 0; i < scoreFromDatabase.size(); i++){
            scores.add(i,scoreFromDatabase.get(i));
        }
    }

public void updateScores(){
       Component current =  DataBase.getInstance().getComponent(courseName.getText().toString(), componentName.getText().toString());
       current.setComponentScores(scores);
}

    public void openCourseComponentsActivity(){
        Intent intent = new Intent(this,CourseComponents.class);
        intent.putExtra("CourseName", courseName.getText().toString());
        startActivity(intent);
    }
}