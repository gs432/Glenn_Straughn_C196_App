package com.example.glenn_straughn_c196_app.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termId;
    private String termName;
    private Date termStart;
    private Date termEnd;

    public Term(int termId, String termName, Date termStart, Date termEnd) {
        this.termId = termId;
        this.termName = termName;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }

    @Override
    public String toString() {
        return "Term{" +
                "termId=" + termId +
                ", termName='" + termName + '\'' +
                ", termStart=" + termStart +
                ", termEnd=" + termEnd +
                '}';
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Date getTermStart() {
        return termStart;
    }

    public void setTermStart(Date termStart) {
        this.termStart = termStart;
    }

    public Date getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(Date termEnd) {
        this.termEnd = termEnd;
    }
}
