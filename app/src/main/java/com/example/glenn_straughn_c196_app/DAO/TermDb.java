package com.example.glenn_straughn_c196_app.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.glenn_straughn_c196_app.Entities.Term;

@Dao
public interface TermDb {

    @Insert
    void insert(Term term);

    @Update
    void update(Term term);

    @Delete
    void delete(Term term);
}