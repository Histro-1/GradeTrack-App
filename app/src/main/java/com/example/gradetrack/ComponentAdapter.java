package com.example.gradetrack;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Component> components =  new ArrayList<>();
    private static String courseName;

    public ComponentAdapter(Context context, String courseName)
    {
        this.context = context;
        this.courseName = courseName;
    }

    public void setComponents(ArrayList<Component> component) {
        this.components = component;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_components, parent, false);
        return new ComponentAdapter.ViewHolder(view);// holder
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        int position = pos;
        holder.componentName.setText(components.get(position).getName() + "\n" + components.get(position).getWeight());
        Typeface typeface = ResourcesCompat.getFont(context, R.font.league_spartan_bold);
        holder.componentName.setTypeface(typeface);

        holder.imgComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddComponentScoreActivity(position);
            }
        });

        holder.componentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddComponentScoreActivity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return components.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView componentName;
        private ImageView imgComponent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            componentName = itemView.findViewById(R.id.component);
            imgComponent = itemView.findViewById(R.id.imgComponent);

        }
    }

    public void openAddComponentScoreActivity(int position){
        Intent intent = new Intent(context, AddComponentScore.class);
        String componentName = components.get(position).getName();


        ArrayList<Integer> scores = components.get(position).getComponentScoresArray();
        intent.putExtra("CourseName",courseName);
        intent.putExtra("ComponentName", componentName);
        intent.putExtra("CurrentScores", scores);
        context.startActivity(intent);


    }
}



