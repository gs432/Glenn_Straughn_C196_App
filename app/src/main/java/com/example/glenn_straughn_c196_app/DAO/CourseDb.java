package com.example.glenn_straughn_c196_app.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.Entities.Term;

import java.util.List;

@Dao
public interface CourseDb {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM COURSES ORDER BY courseID ASC")
    List<Term> getAllCourses();
}
