package com.example.gradetrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CurrentAverageActivity extends AppCompatActivity {

    private TextView txtAverageStatus, highestCourseName, lowestCourseName;
    private ConstraintLayout expandedView,btnExpand, btnCompress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_average);

        initViews();

        setPerformanceDetails();
        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(expandedView);
                expandedView.setVisibility(View.VISIBLE);
                btnExpand.setVisibility(View.GONE);
                btnCompress.setVisibility(View.VISIBLE);
            }
        });

        btnCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(expandedView);
                expandedView.setVisibility(View.GONE);
                btnExpand.setVisibility(View.VISIBLE);
                btnCompress.setVisibility(View.GONE);
            }
        });
    }

    public void initViews(){
        txtAverageStatus = findViewById(R.id.txtAverageStatus);
        highestCourseName = findViewById(R.id.highestCourseName);
        lowestCourseName = findViewById(R.id.lowestCourseName);
        expandedView = findViewById(R.id.expandedView);
        btnCompress = findViewById(R.id.btnCompress);
        btnExpand = findViewById(R.id.btnExpand);
    }

    public void setPerformanceDetails(){

        double currentAverage = Math.round(DataBase.getInstance().getOverallAverage());

        txtAverageStatus.setText("Overall Current Average: "+currentAverage +"%");

        Course max = DataBase.getInstance().getMax();
        Course min = DataBase.getInstance().getMin();

        highestCourseName.setText(max.getName());
        lowestCourseName.setText(min.getName());
    }
}