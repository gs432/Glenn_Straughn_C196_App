package com.example.glenn_straughn_c196_app.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {



    class AssessmentViewHolder extends RecyclerView.ViewHolder{

        private final TextView assessmentItemView;

        private AssessmentViewHolder(View itemView){

            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                final Assessment current = rAssessmentList.get(position);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                Intent intent=new Intent(context,AssessmentList.class);
                intent.putExtra("assessmentId", current.getAssessmentId());
                intent.putExtra("assessmentName", current.getAssessmentName());
                intent.putExtra("assessmentDay", current.getAssessmentStart());
                intent.putExtra("assessmentType", current.getAssessmentType());
                intent.putExtra("courseId", current.getCourseId());
                context.startActivity(intent);
            });
        }
    }

    private List<Assessment> rAssessmentList;
    private final Context context;
    private final LayoutInflater rInflater;

    public AssessmentAdapter(Context context){
        rInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = rInflater.inflate(R.layout.assessment_list_item,parent,false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (rAssessmentList != null){
            Assessment current = rAssessmentList.get(position);
            String name = current.getAssessmentName();
            holder.assessmentItemView.setText(name);
        } else {
            holder.assessmentItemView.setText("No Term Name");
        }
    }

    public void setAssessments(List<Assessment> allAssessments) {
        rAssessmentList = allAssessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (rAssessmentList != null) {
            return rAssessmentList.size();
        }
        else return 0;
    }
}