package com.example.glenn_straughn_c196_app.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.glenn_straughn_c196_app.Database.Repository;
import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AssessmentDetails extends AppCompatActivity {

    Repository repository;
    int assessmentId;
    EditText assessmentName;
    EditText assessmentStart;
    EditText assessmentEnd;
    EditText assessmentType;
    int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.saveAssessment:
                Assessment assessment;
                if (assessmentId == -1) {
                    int newID = repository.getAllAssessments().get(repository.getAllTerms().size() - 1).getAssessmentId() + 1;
                    assessment = new Assessment(newID, assessmentName.getText().toString(), assessmentStart.getText().toString(), assessmentEnd.getText().toString(), assessmentType.getText().toString(), courseId);
                    repository.insert(assessment);
                } else {
                    assessment = new Assessment(assessmentId, assessmentName.getText().toString(), assessmentStart.getText().toString(), assessmentEnd.getText().toString(), assessmentType.getText().toString(), courseId);
                    repository.update(assessment);
                }
                return true;
            case R.id.startNotification:
                String chosenAssessmentStart = assessmentStart.getText().toString();

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
                intent.putExtra("key", "Attention! " + "The assessment entitled " + assessmentName + " starts " + assessmentStart + "!");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.alertCount, intent, 0);
                AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, notifyStartTrigger, pendingIntent);
                return true;
            case R.id.endNotification:
                String chosenAssessmentEnd = assessmentEnd.getText().toString();

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
                intent.putExtra("key", "Attention! " + "The assessment entitled " + assessmentName + " ends " + assessmentEnd + "!");
                pendingIntent = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.alertCount, intent, 0);
                am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, notifyEndTrigger, pendingIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}