package com.example.glenn_straughn_c196_app.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.glenn_straughn_c196_app.Database.Repository;
import com.example.glenn_straughn_c196_app.Entities.Term;
import com.example.glenn_straughn_c196_app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class TermDetails extends AppCompatActivity {

    int termId;
    String termName;
    String termStart;
    String termEnd;
    EditText editTermName;
    EditText editTermStart;
    EditText editTermEnd;
    Repository repository;
    DatePickerDialog.OnDateSetListener termStartDate;
    DatePickerDialog.OnDateSetListener termEndDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        termId = getIntent().getIntExtra("termId", -1);
        termName = getIntent().getStringExtra("termName");
        termStart = getIntent().getStringExtra("termStart");
        termEnd = getIntent().getStringExtra("termEnd");

        editTermName = findViewById(R.id.termName);
        editTermName.setText(termName);
        editTermStart = findViewById(R.id.termStart);
        editTermEnd = findViewById(R.id.termEnd);
        editTermStart.setText(termStart);
        editTermEnd.setText(termEnd);

        repository = new Repository(getApplication());

        termStartDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
            myCalendarStart.set(Calendar.YEAR, year);
            myCalendarStart.set(Calendar.MONTH, monthOfYear);
            myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateStartLabel();
        };

        termEndDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
            myCalendarEnd.set(Calendar.YEAR, year);
            myCalendarEnd.set(Calendar.MONTH, monthOfYear);
            myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateEndLabel();
        };

        editTermStart.setOnClickListener(view -> new DatePickerDialog(TermDetails.this, termStartDate, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show());

        editTermEnd.setOnClickListener(view -> new DatePickerDialog(TermDetails.this, termEndDate, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH), myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.saveTerm:
                Term term;
                if (termId == -1) {
                    int newID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() + 1;
                    term = new Term(newID, editTermName.getText().toString(), editTermStart.getText().toString(), editTermEnd.getText().toString());
                    repository.insert(term);
                } else {
                    term = new Term(termId, editTermName.getText().toString(), editTermStart.getText().toString(), editTermEnd.getText().toString());
                    repository.update(term);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateStartLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTermStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateEndLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTermEnd.setText(sdf.format(myCalendarEnd.getTime()));
    }

    public void enterCourseList(View view) {
        Intent intent=new Intent(TermDetails.this,CourseList.class);
        startActivity(intent);
    }
}