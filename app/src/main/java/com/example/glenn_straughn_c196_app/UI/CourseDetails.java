package com.example.glenn_straughn_c196_app.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.glenn_straughn_c196_app.R;

public class CourseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
    }

    public void enterAssessmentList(View view) {
        Intent intent=new Intent(CourseDetails.this,AssessmentList.class);
        startActivity(intent);
    }

    public void saveCourseBtn(View view) {
    }
}