package com.example.gradetrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddComponentAdapter extends RecyclerView.Adapter<AddComponentAdapter.ViewHolder>{

    private ArrayList<Integer> componentScores = new ArrayList<>();
    private Context context;
    String componentName;

    public AddComponentAdapter(Context context, String componentName){
        this.context = context;
        this.componentName = componentName;
    }

    public void setComponentScores(ArrayList<Integer> componentScores) {
        this.componentScores = componentScores;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_entry, parent, false);
        return new AddComponentAdapter.ViewHolder(view);// holder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        int position = pos;
        holder.edtScore.setVisibility(View.GONE);
        holder.imgAddBtn.setVisibility(View.GONE);
        holder.imgRemoveBtn.setVisibility(View.VISIBLE);
        holder.resultScore.setVisibility(View.VISIBLE);
        holder.txtScore.setText(this.componentName +" " + (position + 1)); //TODO should say Quiz 1, Quiz 2, Quiz 3...
        holder.resultScore.setText(componentScores.get(position) +"");

        holder.imgRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO add condition that removes score only when the submit button is removed. Submit button is not part of the layout however.
                componentScores.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return componentScores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtScore, resultScore;
        private EditText edtScore;
        private ImageView imgAddBtn, imgRemoveBtn;


        public ViewHolder(@NonNull View itemView) {
                super(itemView);

                txtScore = itemView.findViewById(R.id.txtScore);
                resultScore = itemView.findViewById(R.id.resultScore);
                edtScore = itemView.findViewById(R.id.edtScore);
                imgAddBtn = itemView.findViewById(R.id.imgAddBtn);
                imgRemoveBtn = itemView.findViewById(R.id.imgRemoveBtn);

        }
    }


}
