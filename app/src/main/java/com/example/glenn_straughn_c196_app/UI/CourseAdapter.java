package com.example.glenn_straughn_c196_app.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {



    class CourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView courseItemView;

        private CourseViewHolder(View itemView){

            super(itemView);
            courseItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(view -> {
                int position=getAdapterPosition();
                final Course current= rCourseList.get(position);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                Intent intent=new Intent(context,TermList.class);
                intent.putExtra("courseId", current.getCourseId());
                intent.putExtra("courseName", current.getCourseName());
                intent.putExtra("courseStart", current.getCourseStart());
                intent.putExtra("courseEnd", current.getCourseEnd());
                intent.putExtra("courseStatus", current.getCourseStatus());
                intent.putExtra("instructorName", current.getInstructorName());
                intent.putExtra("instructorEmail", current.getInstructorEmail());
                intent.putExtra("instructorPhone", current.getInstructorPhone());
                intent.putExtra("courseNote", current.getCourseNote());
                intent.putExtra("termId", current.getTermId());
                context.startActivity(intent);
            });
        }
    }

    private List<Course> rCourseList;
    private final Context context;
    private final LayoutInflater rInflater;

    public CourseAdapter(Context context){
        rInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = rInflater.inflate(R.layout.course_list_item,parent,false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (rCourseList != null){
            Course current = rCourseList.get(position);
            String name = current.getCourseName();
            holder.courseItemView.setText(name);
        } else {
            holder.courseItemView.setText("No Course Name");
        }
    }

    public void setCourses(List<Course> allCourses) {
        rCourseList = allCourses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (rCourseList != null) {
            return rCourseList.size();
        }
        else return 0;
    }
}
