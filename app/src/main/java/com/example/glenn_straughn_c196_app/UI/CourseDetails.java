package com.example.glenn_straughn_c196_app.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.glenn_straughn_c196_app.Database.Repository;
import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.Entities.Term;
import com.example.glenn_straughn_c196_app.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class CourseDetails extends AppCompatActivity {

    int courseId;
    String courseName;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    String courseNote;
    int termId;
    EditText editCourseName;
    EditText editCourseStart;
    EditText editCourseEnd;
    EditText editCourseStatus;
    EditText editInstructorName;
    EditText editInstructorPhone;
    EditText editInstructorEmail;
    EditText editCourseNote;
    Repository repository;
    DatePickerDialog.OnDateSetListener courseStartDate;
    DatePickerDialog.OnDateSetListener courseEndDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        courseId = getIntent().getIntExtra("courseId", -1);
        courseName = getIntent().getStringExtra("courseName");
        courseStart = getIntent().getStringExtra("courseStart");
        courseEnd = getIntent().getStringExtra("courseEnd");
        courseStatus = getIntent().getStringExtra("courseStatus");
        instructorName = getIntent().getStringExtra("instructorName");
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorEmail = getIntent().getStringExtra("instructorEmail");
        courseNote = getIntent().getStringExtra("courseNote");
        termId = getIntent().getIntExtra("termId", -1);

        /*
        editCourseName.findViewById(R.id.courseName);
        editCourseName.setText(courseName);
        editCourseStart.findViewById(R.id.courseStart);
        editCourseStart.setText(courseStart);
        editCourseEnd.findViewById(R.id.courseEnd);
        editCourseEnd.setText(courseEnd);
        editCourseStatus.findViewById(R.id.courseStatus);
        editCourseStatus.setText(courseStatus);
        editInstructorName.findViewById(R.id.instructorName);
        editInstructorName.setText(instructorName);
        editInstructorPhone.findViewById(R.id.instructorPhone);
        editInstructorPhone.setText(instructorPhone);
        editInstructorEmail.findViewById(R.id.instructorEmail);
        editInstructorEmail.setText(instructorEmail);
        editCourseNote.findViewById(R.id.courseNote);
        editCourseNote.setText(courseNote);

         */

        repository = new Repository(getApplication());

        courseStartDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
            myCalendarStart.set(Calendar.YEAR, year);
            myCalendarStart.set(Calendar.MONTH, monthOfYear);
            myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateStartLabel();
        };

        courseEndDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
            myCalendarEnd.set(Calendar.YEAR, year);
            myCalendarEnd.set(Calendar.MONTH, monthOfYear);
            myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateEndLabel();
        };

        //editCourseStart.setOnClickListener(view -> new DatePickerDialog(CourseDetails.this, courseStartDate, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show());

        //editCourseEnd.setOnClickListener(view -> new DatePickerDialog(CourseDetails.this, courseEndDate, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH), myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.saveCourse:
                Course course;
                if (courseId == -1) {
                    int newID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() + 1;
                    course = new Course(newID, editCourseName.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), editCourseStatus.getText().toString(), editInstructorName.getText().toString(), editInstructorPhone.getText().toString(), editInstructorEmail.getText().toString(), editCourseNote.getText().toString(), termId);
                    repository.insert(course);
                } else {
                    course = new Course(courseId, editCourseName.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), editCourseStatus.getText().toString(), editInstructorName.getText().toString(), editInstructorPhone.getText().toString(), editInstructorEmail.getText().toString(), editCourseNote.getText().toString(), termId);
                    repository.update(course);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateStartLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editCourseStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateEndLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editCourseEnd.setText(sdf.format(myCalendarEnd.getTime()));
    }

    public void enterAssessmentList(View view) {
        Intent intent=new Intent(CourseDetails.this,AssessmentList.class);
        startActivity(intent);
    }
}