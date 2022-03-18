package com.example.glenn_straughn_c196_app.Database;

import android.app.Application;

import com.example.glenn_straughn_c196_app.DAO.AssessmentDb;
import com.example.glenn_straughn_c196_app.DAO.CourseDb;
import com.example.glenn_straughn_c196_app.DAO.TermDb;
import com.example.glenn_straughn_c196_app.Entities.Assessment;
import com.example.glenn_straughn_c196_app.Entities.Course;
import com.example.glenn_straughn_c196_app.Entities.Term;

import java.util.List;

public class Repository {
    private TermDb rTerm;
    private List<Term> rTermList;
    private AssessmentDb rAssessment;
    private List<Assessment> rAssessmentList;
    private CourseDb rCourse;
    private List<Course> rCourseList;

    public Repository(Application application) {

    }
}
