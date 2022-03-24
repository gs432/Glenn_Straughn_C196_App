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
import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.Entities.Term;
import com.example.glenn_straughn_c196_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseList extends AppCompatActivity {

    int termId;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        termId = getIntent().getIntExtra("termId", -1);
        repository=new Repository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourses= new ArrayList<>();
        for(Course course : repository.getAllCourses()){
            if(course.getTermId() == termId)filteredCourses.add(course);
        }
        courseAdapter.setCourses(filteredCourses);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.courseRefresh:
                repository=new Repository(getApplication());
                List<Course> filteredCourses= new ArrayList<>();
                for(Course course : repository.getAllCourses()){
                    if(course.getTermId() == termId)filteredCourses.add(course);
                }
               // courseAdapter.setCourses(filteredCourses);
                RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
                final CourseAdapter courseAdapter = new CourseAdapter(this);
                recyclerView.setAdapter(courseAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                courseAdapter.setCourses(filteredCourses);
        }

        return super.onOptionsItemSelected(item);
    }

    public void enterNewCourse(View view) {
        Intent intent = new Intent(CourseList.this,CourseDetails.class);
        startActivity(intent);
    }
}