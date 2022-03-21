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
import com.example.glenn_straughn_c196_app.Entities.Term;
import com.example.glenn_straughn_c196_app.R;

import java.util.List;

public class TermList extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repository=new Repository(getApplication());
        List<Term> allTerms = repository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.termrecyclerview);

        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.refresh:
                repository=new Repository(getApplication());
                List<Term> allTerms = repository.getAllTerms();
                RecyclerView recyclerView = findViewById(R.id.recyclerview);
                final TermAdapter termAdapter=new TermAdapter(this);
                recyclerView.setAdapter(termAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                termAdapter.setTerms(allTerms);
        }

        return super.onOptionsItemSelected(item);
    }

    public void enterPartsList(View view) {
        Intent intent=new Intent(TermList.this,CourseList.class);
        startActivity(intent);
    }
}