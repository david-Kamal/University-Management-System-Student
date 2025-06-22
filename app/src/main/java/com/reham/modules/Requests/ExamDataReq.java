package com.reham.modules.Requests;

public class ExamDataReq
{
    private int studentid;

    public ExamDataReq(int studentid) {
        this.studentid = studentid;
    }

    public int getStudentid()
    {
        return studentid;
    }
}
