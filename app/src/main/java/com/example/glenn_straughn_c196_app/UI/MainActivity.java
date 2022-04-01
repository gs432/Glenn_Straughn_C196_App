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




    }

    public void enterClick(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
    }
}