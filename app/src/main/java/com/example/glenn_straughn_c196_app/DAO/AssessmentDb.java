package com.example.glenn_straughn_c196_app.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.Entities.Term;

import java.util.List;

@Dao
public interface AssessmentDb {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM ASSESSMENTS ORDER BY assessmentID ASC")
    List<Term> getAllAssessments();
}
