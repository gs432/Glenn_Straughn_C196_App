package com.example.glenn_straughn_c196_app.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.glenn_straughn_c196_app.Database.Repository;
import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.Entities.Term;
import com.example.glenn_straughn_c196_app.R;

import java.util.List;
import java.util.Objects;

public class AssessmentList extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repository=new Repository(getApplication());
        List<Assessment> allAssessments = repository.getAllAssessments();
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);

        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(allAssessments);
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
        startActivity(intent);
    }
}