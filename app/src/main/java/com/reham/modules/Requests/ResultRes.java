package com.reham.modules.Requests;

public class ResultRes
{
    private String subjectname, result;

    public ResultRes(String subjectname, String result)
    {
        this.subjectname = subjectname;
        this.result = result;
    }

    public String getSubjectname()
    {
        return subjectname;
    }

    public String getResult()
    {
        return result;
    }
}
