package com.example.glenn_straughn_c196_app.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.glenn_straughn_c196_app.DAO.AssessmentDb;
import com.example.glenn_straughn_c196_app.DAO.CourseDb;
import com.example.glenn_straughn_c196_app.DAO.TermDb;
import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.Entities.Term;

import java.net.ContentHandler;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 1,exportSchema = false)
public abstract class DbBuilder extends RoomDatabase {

    public abstract TermDb termDb();
    public abstract CourseDb courseDb();
    public abstract AssessmentDb assessmentDb();

    static DbBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DbBuilder.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DbBuilder.class, "TermDb.db")
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }

}
