package com.example.glenn_straughn_c196_app.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentId;
    private String assessmentName;
    private Date assessmentDay;
    private String assessmentType;
    private int courseId;

    public Assessment(int assessmentId, String assessmentName, Date assessmentDay, String assessmentType, int courseId){
        this.assessmentId = assessmentId;
        this.assessmentName = assessmentName;
        this.assessmentDay = assessmentDay;
        this.assessmentType = assessmentType;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId=" + assessmentId +
                ", assessmentName='" + assessmentName + '\'' +
                ", assessmentDay=" + assessmentDay +
                ", assessmentType='" + assessmentType + '\'' +
                ", courseId=" + courseId +
                '}';
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public Date getAssessmentDay() {
        return assessmentDay;
    }

    public void setAssessmentDay(Date assessmentDay) {
        this.assessmentDay = assessmentDay;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
