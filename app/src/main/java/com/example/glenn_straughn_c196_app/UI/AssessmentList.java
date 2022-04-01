package com.example.glenn_straughn_c196_app.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.glenn_straughn_c196_app.Database.Repository;
import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.Entities.Term;
import com.example.glenn_straughn_c196_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssessmentList extends AppCompatActivity {

    int courseId;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        courseId = getIntent().getIntExtra("courseId", -1);
        repository=new Repository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessment> filteredAssessments= new ArrayList<>();
        for(Assessment assessment : repository.getAllAssessments()){
            if(assessment.getCourseId() == courseId)filteredAssessments.add(assessment);
        }
        assessmentAdapter.setAssessments(filteredAssessments);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessment_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.assessmentRefresh:
                /*
                repository=new Repository(getApplication());
                List<Assessment> filteredAssessments = new ArrayList<>();
                for (Assessment a : repository.getAllAssessments()){
                    if (a.getCourseId() == courseId)filteredAssessments.add(a);
                }
                RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
                final AssessmentAdapter assessmentAdapter=new AssessmentAdapter(this);
                recyclerView.setAdapter(assessmentAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                assessmentAdapter.setAssessments(filteredAssessments);
                Toast.makeText(getApplicationContext(), "Assessment List refreshed!", Toast.LENGTH_LONG).show();

                 */



                
                repository=new Repository(getApplication());
                List<Assessment> allAssessments = repository.getAllAssessments();
                RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
                final AssessmentAdapter assessmentAdapter=new AssessmentAdapter(this);
                recyclerView.setAdapter(assessmentAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                assessmentAdapter.setAssessments(allAssessments);





        }

        return super.onOptionsItemSelected(item);
    }

    public void enterNewAssessment(View view){
        Intent intent = new Intent(AssessmentList.this, AssessmentDetails.class);
        intent.putExtra("selectedCourseId", courseId);
        startActivity(intent);
    }
}