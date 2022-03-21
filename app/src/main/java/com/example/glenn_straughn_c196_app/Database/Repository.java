package com.example.glenn_straughn_c196_app.Database;

import android.app.Application;

import com.example.glenn_straughn_c196_app.DAO.AssessmentDao;
import com.example.glenn_straughn_c196_app.DAO.CourseDao;
import com.example.glenn_straughn_c196_app.DAO.TermDao;
import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.Entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDao rTerm;
    private List<Term> rTermList;
    private CourseDao rCourse;
    private List<Course> rCourseList;
    private AssessmentDao rAssessment;
    private List<Assessment> rAssessmentList;
    private static int THREAD_COUNT=4;
    private static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

    public Repository(Application application) {
        DbBuilder db = DbBuilder.getDatabase(application);
        rTerm = db.termDb();
        rCourse = db.courseDb();
        rAssessment = db.assessmentDb();
    }

    public List<Term>getAllTerms(){
        databaseExecutor.execute(()->{
            rTermList = rTerm.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rTermList;
    }

    public void insert(Term term){
        databaseExecutor.execute(()->{
            rTerm.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Term term){
        databaseExecutor.execute(()->{
            rTerm.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Term term){
        databaseExecutor.execute(()->{
            rTerm.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public List<Course> getAllCourses(){
        databaseExecutor.execute(()->{
            rCourseList = rCourse.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rCourseList;
    }

    public void insert(Course course){
        databaseExecutor.execute(()->{
            rCourse.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Course course){
        databaseExecutor.execute(()->{
            rCourse.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course){
        databaseExecutor.execute(()->{
            rCourse.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public List<Assessment>getAllAssessments(){
        databaseExecutor.execute(()->{
            rAssessmentList = rAssessment.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rAssessmentList;
    }

    public void insert(Assessment assessment){
        databaseExecutor.execute(()->{
            rAssessment.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            rAssessment.update(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment){
        databaseExecutor.execute(()->{
            rAssessment.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
