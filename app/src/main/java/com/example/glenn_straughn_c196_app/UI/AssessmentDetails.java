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
    EditText editAssessmentName;
    EditText editAssessmentStart;
    EditText editAssessmentEnd;

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
                    assessment = new Assessment(newID, editAssessmentName.getText().toString(), editAssessmentStart.getText().toString(), editAssessmentEnd.getText().toString());
                    repository.insert(assessment);
                } else {
                    assessment = new Assessment(assessmentId, editAssessmentName.getText().toString(), editAssessmentStart.getText().toString(), editAssessmentEnd.getText().toString());
                    repository.update(assessment);
                }
                return true;
            case R.id.notification:
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
                intent.putExtra("key", "Attention! " + "The assessment entitled " + editAssessmentName + " starts " + editAssessmentStart + "!");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, notifyStartTrigger, pendingIntent);
                return true;
            case R.id.notification:
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
                intent.putExtra("key", "Attention! " + "The assessment entitled " + editAssessmentName + " ends " + editAssessmentEnd + "!");
                pendingIntent = PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.numAlert, intent, 0);
                am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, notifyEndTrigger, pendingIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}