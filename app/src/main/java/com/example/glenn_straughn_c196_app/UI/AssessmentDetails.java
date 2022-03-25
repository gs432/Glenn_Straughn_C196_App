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
import android.widget.EditText;
import android.widget.Toast;

import com.example.glenn_straughn_c196_app.Database.Repository;
import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AssessmentDetails extends AppCompatActivity {

    Repository repository;
    int assessmentId;
    String assessmentName;
    String assessmentStart;
    String assessmentEnd;
    String assessmentType;
    EditText editAssessmentName;
    EditText editAssessmentStart;
    EditText editAssessmentEnd;
    EditText editAssessmentType;
    int courseId;
    DatePickerDialog.OnDateSetListener assessmentStartDate;
    DatePickerDialog.OnDateSetListener assessmentEndDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
    Assessment selectedAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        assessmentId = getIntent().getIntExtra("assessmentId", assessmentId);
        assessmentName = getIntent().getStringExtra("assessmentName");
        assessmentStart = getIntent().getStringExtra("assessmentStart");
        assessmentEnd = getIntent().getStringExtra("assessmentEnd");
        assessmentType = getIntent().getStringExtra("assessmentType");
        courseId = getIntent().getIntExtra("courseId", courseId);

        editAssessmentName = findViewById(R.id.assessmentName);
        editAssessmentName.setText(assessmentName);
        editAssessmentStart = findViewById(R.id.assessmentStart);
        editAssessmentStart.setText(assessmentStart);
        editAssessmentEnd = findViewById(R.id.assessmentEnd);
        editAssessmentEnd.setText(assessmentEnd);
        editAssessmentType = findViewById(R.id.assessmentType);
        editAssessmentType.setText(assessmentType);

        repository = new Repository(getApplication());

        assessmentStartDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
            myCalendarStart.set(Calendar.YEAR, year);
            myCalendarStart.set(Calendar.MONTH, monthOfYear);
            myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateStartLabel();
        };

        assessmentEndDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
            myCalendarEnd.set(Calendar.YEAR, year);
            myCalendarEnd.set(Calendar.MONTH, monthOfYear);
            myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateEndLabel();
        };

        editAssessmentStart.setOnClickListener(view -> new DatePickerDialog(AssessmentDetails.this, assessmentStartDate, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show());

        editAssessmentEnd.setOnClickListener(view -> new DatePickerDialog(AssessmentDetails.this, assessmentEndDate, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH), myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show());

        for (Assessment a : repository.getAllAssessments()) {
            if (a.getAssessmentId() == assessmentId) {
                selectedAssessment = a;
            }
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessment_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.saveAssessment:
                Assessment assessment;
                if (assessmentId == -1) {
                    int newID = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentId() + 1;
                    assessment = new Assessment(newID, editAssessmentName.getText().toString(), editAssessmentStart.getText().toString(), editAssessmentEnd.getText().toString(), editAssessmentType.getText().toString(), courseId);
                    repository.insert(assessment);
                    Toast.makeText(getApplicationContext(), "Assessment saved. Select refresh from Assessment List menu", Toast.LENGTH_LONG).show();
                } else {
                    assessment = new Assessment(assessmentId, editAssessmentName.getText().toString(), editAssessmentStart.getText().toString(), editAssessmentEnd.getText().toString(), editAssessmentType.getText().toString(), courseId);
                    repository.update(assessment);
                    Toast.makeText(getApplicationContext(), "Assessment updated. Select refresh from Assessment List menu", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.startNotification:
                String chosenAssessmentStart = editAssessmentStart.getText().toString();

                String dateFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

                Date notificationStartDate = null;
                try {
                    notificationStartDate = sdf.parse(chosenAssessmentStart);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long notifyStartTrigger = notificationStartDate.getTime();
                Intent intent = new Intent(AssessmentDetails.this, Receiver.class);
                intent.putExtra("startNotice", "Attention! " + "The assessment entitled " + assessmentName + " starts " + assessmentStart + "!");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.alertCount, intent, 0);
                AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, notifyStartTrigger, pendingIntent);
                Toast.makeText(getApplicationContext(), "You will be notified of this assessment's start date.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.endNotification:
                String chosenAssessmentEnd = editAssessmentEnd.getText().toString();

                dateFormat = "MM/dd/yy";
                sdf = new SimpleDateFormat(dateFormat, Locale.US);

                Date notificationEndDate = null;
                try {
                    notificationEndDate = sdf.parse(chosenAssessmentEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long notifyEndTrigger = notificationEndDate.getTime();
                intent = new Intent(AssessmentDetails.this, Receiver.class);
                intent.putExtra("endNotice", "Attention! " + "The assessment entitled " + assessmentName + " ends " + assessmentEnd + "!");
                pendingIntent = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.alertCount, intent, 0);
                am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, notifyEndTrigger, pendingIntent);
                Toast.makeText(getApplicationContext(), "You will be notified of this assessment's end date.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.deleteAssessment:
                repository.delete(selectedAssessment);
                Toast.makeText(getApplicationContext(), "Assessment deleted!", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateStartLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editAssessmentStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateEndLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editAssessmentEnd.setText(sdf.format(myCalendarEnd.getTime()));
    }

}