package com.example.gradetrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AddComponentActivity extends AppCompatActivity {
    private EditText edtComponentName, edtComponentWeight,edtScore;
    private TextView txtEntryExplanation, courseName, txtQuizWarning, txtWeightWarning;
    private ArrayList<Integer> componentScores = new ArrayList<>();
    private int lastScoreIndex; // holds the index of the last score before the user decided to add or remove the scores from the list.
    private FloatingActionButton sendComponentBtn, cancelBtn;
    private ImageView imgAddBtn, imgRemoveBtn;
    private RecyclerView recScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);
        
        initView();

//-------------------------Adding sample scores to componentScores----------------------------------------//

//        componentScores.add(10);
//        componentScores.add(20);
//        componentScores.add(100);

//---------------------------------------ADAPTER------------------------------------------------------------//

        AddComponentAdapter adapter = new AddComponentAdapter(this,"Score");
        adapter.setComponentScores(componentScores);
        recScores.setAdapter(adapter);
        recScores.setLayoutManager(new LinearLayoutManager(this));

//----------------------------------------BUTTON PUSH-------------------------------------------------------//
/*
    imgAddBtn is the small + button, when pushed should add whatever is entered in edtScore to a new row
 */
        imgAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(AddComponentActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
                int score = Integer.parseInt(edtScore.getText().toString());
                componentScores.add(score);
                // update adapter...
                adapter.setComponentScores(componentScores);
            }
        });

        edtScore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                txtEntryExplanation.setVisibility(View.GONE);
                return false;
            }
        });

        sendComponentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()){
                    createNewComponent();
                    openCourseComponentsActivity();
                }else{
                    Toast.makeText(AddComponentActivity.this, "Check entries", Toast.LENGTH_SHORT).show();
                }

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCourseComponentsActivity();
            }
        });



    }
    
    public void initView(){
        edtComponentName = findViewById(R.id.edtComponentName);
        edtComponentWeight = findViewById(R.id.edtComponentWeight);
        edtScore = findViewById(R.id.edtScore);
        txtEntryExplanation = findViewById(R.id.txtEntryExplanation);
        txtQuizWarning = findViewById(R.id.txtQuizWarning);
        txtWeightWarning = findViewById(R.id.txtWeightWarning);
        courseName = findViewById(R.id.courseName);
        sendComponentBtn = findViewById(R.id.sendComponentBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        imgAddBtn = findViewById(R.id.imgAddBtn);
        imgRemoveBtn = findViewById(R.id.imgRemoveBtn);
        recScores = findViewById(R.id.recScores);



        // assinging name of the course
        courseName.setText(getIntent().getStringExtra("CourseName"));

        // we will not be getting the component scores from the data base because this page should only appear when we have a completely new component to add.
        // componentScores = DataBase.getInstance().getComponentScores();

    }

    public boolean isValid(){
        //TODO check if the entry is valid ... may need to add more text views
        boolean valid = true;
        try {
            String compName = edtComponentName.getText().toString();

            // Set warning text invisible
            txtQuizWarning.setVisibility(View.GONE);
            txtWeightWarning.setVisibility(View.GONE);

            if(compName.isEmpty()){
                txtQuizWarning.setText("Enter component name*");
                txtQuizWarning.setVisibility(View.VISIBLE);
                valid = false;
            }

            double compWeight = Double.parseDouble(edtComponentWeight.getText().toString());

            if(compWeight < 0){
                txtWeightWarning.setText("Invalid Component weight*");
                txtWeightWarning.setVisibility(View.VISIBLE);
                 valid = false;
            }

            }catch (Exception e){
            txtWeightWarning.setText("Enter component weight*");
            txtWeightWarning.setVisibility(View.VISIBLE);
            valid = false;
        }


        return valid;
    }

    public void createNewComponent(){

        String courseName = this.courseName.getText().toString();
        String componentName = edtComponentName.getText().toString();
        double weight = Double.parseDouble(edtComponentWeight.getText().toString());

        if(DataBase.getInstance().createNewComponent(courseName, componentName,componentScores, weight)){
            Toast.makeText(this, "Component added", Toast.LENGTH_SHORT).show();
        }
    }


    public void openCourseComponentsActivity(){
        Intent intent = new Intent(this,CourseComponents.class);
        intent.putExtra("CourseName", courseName.getText().toString());
        startActivity(intent);
    }
}