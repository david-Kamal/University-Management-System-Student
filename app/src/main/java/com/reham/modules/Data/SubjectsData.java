package com.reham.modules.Data;

public class SubjectsData
{
    private int id, hours;
    private String subjectname, date, date2;

    public SubjectsData(int id, int hours, String subjectname, String date, String date2) {
        this.id = id;
        this.hours = hours;
        this.subjectname = subjectname;
        this.date = date;
        this.date2 = date2;
    }

    public int getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getDate() {
        return date;
    }

    public String getDate2() {
        return date2;
    }
}
