package com.reham.modules;


import com.reham.modules.Data.ExamData;
import com.reham.modules.Data.ResultData;
import com.reham.modules.Data.StudentIdReq;
import com.reham.modules.Data.SubjectsData;
import com.reham.modules.Requests.ExamDataReq;
import com.reham.modules.Requests.LoginRequest;
import com.reham.modules.Requests.LoginRes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IEndPoints
{
//    @POST("adddoctorsubject.php")
//    Call<Void> adddoctorsubject (@Body AddDoctorSubjectReq addDoctorSubjectReq);
//    @POST("alwanadduser.php")
//    Call<Void> alwanadduser (@Body AddStudentReq addStudentReq);
//    @POST("dadduser.php")
//    Call<Void> dadduser (@Body AddUserReq addUserReq);
//    @POST("addexam.php")
//    Call<Void> addexam (@Body AddExamReq addExamReq);
//    @POST("adddoc.php")
//    Call<Void> adddoc (@Body AddDocReq addDocReq);

    @POST("studentlogin.php")
    Call<LoginRes> studentLogin (@Body LoginRequest loginRequest);

    @POST("returnallsubjects.php")
    Call<ArrayList<SubjectsData>> returnallsubjects (@Body String s);

    @POST("returnExamData.php")
    Call<ArrayList<ExamData>> returnExamData (@Body ExamDataReq examDataReq);

    @POST("returnResult.php")
    Call<ArrayList<ResultData>> returnResult (@Body StudentIdReq studentIdReq);

    @POST("remainingsubjects.php")
    Call<ArrayList<SubjectsData>> remainingsubjects (@Body StudentIdReq studentIdReq);

    @POST("attendance.php")
    Call<ArrayList<SubjectsData>> attendance (@Body StudentIdReq studentIdReq);


}
