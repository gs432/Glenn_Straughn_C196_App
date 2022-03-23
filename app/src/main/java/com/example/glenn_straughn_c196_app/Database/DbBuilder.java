package com.example.glenn_straughn_c196_app.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.glenn_straughn_c196_app.DAO.AssessmentDao;
import com.example.glenn_straughn_c196_app.DAO.CourseDao;
import com.example.glenn_straughn_c196_app.DAO.TermDao;
import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.Entities.Term;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 2,exportSchema = false)
public abstract class DbBuilder extends RoomDatabase {

    private static volatile DbBuilder INSTANCE;
    public abstract TermDao termDb();
    public abstract CourseDao courseDb();
    public abstract AssessmentDao assessmentDb();

    static DbBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DbBuilder.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DbBuilder.class, "TrackerDb.db")
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }

}
