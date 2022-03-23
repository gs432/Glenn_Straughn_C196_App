package com.example.glenn_straughn_c196_app.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.glenn_straughn_c196_app.Database.Repository;
import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.Entities.Term;
import com.example.glenn_straughn_c196_app.R;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static int alertCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Repository repo=new Repository(getApplication());
        /*
        Term term = new Term(1, "term1", "1/2/23", "1/3/24");
        repo.insert(term);

        Course course = new Course(1, "Course1", "2/3/23", "3/3/24", "enrolled", "Sher", "5560026", "mail@mail.com", " ", 1);
        repo.insert(course);

        Term term2 = new Term(2, "term2", "2/2/23", "2/3/24");
        repo.insert(term2);

        Term term3 = new Term(3, "term3", "3/2/23", "3/3/24");
        repo.insert(term3);

         */





    }

    public void enterClick(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
    }
}