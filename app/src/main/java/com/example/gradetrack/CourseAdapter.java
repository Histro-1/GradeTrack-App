package com.example.gradetrack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private ArrayList<Course> courses = new ArrayList<>();
    private Context context;
    private ArrayList<Component> assessments;


    /*
    Context tells us about the surrounding information where this adapter will be used

    This allows us to access resources, interact with other Android components by sending messages
    gives us information about the app environment

    "Interface to global information about an application environment.
     This is an abstract class whose implementation is provided by the Android system.
     It allows access to application-specific resources and classes, as well as up-calls for
     application-level operations such as launching activities, broadcasting and receiving intents, etc."

     */

    public CourseAdapter(Context context){
        this.context = context;
    }

    @NonNull //The compiler can determine cases where a code path might receive a null value, without ever having to debug a NullPointerException.
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courseitems, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView")  int position) {

            holder.courseName.setText(courses.get(position).getName());
            Typeface typeface = ResourcesCompat.getFont(context, R.font.league_spartan_bold); //setting font of the course names
            holder.courseName.setTypeface(typeface);
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, courses.get(position).getName() + " is a course you are enrolled in", Toast.LENGTH_SHORT).show();
                         openAddCourseActivity(position);

                }
            });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourses(ArrayList<Course> courses){
        this.courses = courses;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private TextView courseName;

        public ViewHolder(@NonNull View itemView){
                super(itemView);
                parent = itemView.findViewById(R.id.courseItem);
                courseName = itemView.findViewById(R.id.courseName);
        }

    }


/*
The "openAddCourseActivity" method is custom(by me)... and allows you to open a new page activity for every item in the recycler view
 */

    private void openAddCourseActivity( int position){
        Intent intent = new Intent(context, CourseComponents.class);
        String courseName = courses.get(position).getName();
        assessments = courses.get(position).getAssessments();
        intent.putExtra("CourseName", courseName);

        context.startActivity(intent);
       // // get the assessments of the current course

       // double average = courses.get(position).getCurrentAverage();


        //TODO put course average
       // intent.putExtra("CourseAverage", average);

    }
}
