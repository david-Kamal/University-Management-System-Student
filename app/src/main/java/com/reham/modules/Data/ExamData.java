package com.reham.modules.Data;

public class ExamData
{
//    "id": "1",
//            "subjectid": "3",
//            "date": "2019-04-27",
//            "time": "5"

    private int id, subjectid;
    private String date,time;

    public ExamData(int id, int subjectid, String date, String time)
    {
        this.id = id;
        this.subjectid = subjectid;
        this.date = date;
        this.time = time;
    }

    public int getId()
    {
        return id;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
