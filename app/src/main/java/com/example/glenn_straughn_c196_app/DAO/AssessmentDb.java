package com.example.glenn_straughn_c196_app.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.glenn_straughn_c196_app.Entities.Assessment;

@Dao
public interface AssessmentDb {

    @Insert()
    void insert(Assessment assessment);

    @Update()
    void update(Assessment assessment);

    @Delete()
    void delete(Assessment assessment);
}
