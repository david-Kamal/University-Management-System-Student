package com.reham.modules;

import android.util.Log;

import com.reham.modernacademystudent.MainActivity;
import com.reham.modules.Data.ExamData;
import com.reham.modules.Data.StudentIdReq;
import com.reham.modules.Data.SubjectsData;
import com.reham.modules.Requests.ExamDataReq;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonModule
{
    private  static ArrayList<ExamData> examDataArrayList = new ArrayList<>();;
    private static ArrayList<SubjectsData> subjectsDataArrayList;
    private  static ArrayList<SubjectsData> remainingSubjectsDataArrayList= new ArrayList<>();;
    private  static ArrayList<SubjectsData> attendanceArrayList = new ArrayList<>();;

    public static ArrayList<ExamData> getExamDataArrayList()
    {
        return examDataArrayList;
    }
    public static ArrayList<SubjectsData> getSubjectsDataArrayList() {
        return subjectsDataArrayList;
    }

    public static ArrayList<SubjectsData> getRemainingSubjectsDataArrayList() {
        return remainingSubjectsDataArrayList;


    }

    public void getSubjectsData(IRetrofitResponseListener iRetrofitResponseListener)
    {
        Call<ArrayList<SubjectsData>> call = MainActivity.client.returnallsubjects(" ");
        try
        {
            call.enqueue(new Callback<ArrayList<SubjectsData>>() {
                @Override
                public void onResponse(Call<ArrayList<SubjectsData>> call, Response<ArrayList<SubjectsData>> response)
                {
                    System.out.println(response.isSuccessful());
                    if (response.isSuccessful())
                    {
                        subjectsDataArrayList = new ArrayList<>(response.body());
                        iRetrofitResponseListener.onSuccess();
                    }else
                    {
                        System.out.println(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<SubjectsData>> call, Throwable t)
                {
                    Log.e("getSubjectsData", t.getMessage());
                    iRetrofitResponseListener.onFailure();
                }
            });

        }catch (Exception ex)
        {
            Log.e("getSubjectsData", ex.getMessage());
        }
    }

    public void getExamsData(int studentid)
    {
        ExamDataReq examDataReq = new ExamDataReq(studentid);
        Call<ArrayList<ExamData>> call = MainActivity.client.returnExamData(examDataReq);
        call.enqueue(new Callback<ArrayList<ExamData>>()
        {
            @Override
            public void onResponse(Call<ArrayList<ExamData>> call, Response<ArrayList<ExamData>> response)
            {
                if (response.isSuccessful())
                {
                    examDataArrayList = new ArrayList<>(response.body());
                }else
                {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ExamData>> call, Throwable t)
            {
                Log.e("getSubjectsData", t.getMessage());
            }
        });
    }

    public static ArrayList<SubjectsData> getAttendanceArrayList() {
        return attendanceArrayList;
    }

    public void getRemainingData(int studentid)
    {
        StudentIdReq studentIdReq= new StudentIdReq(studentid);
        Call<ArrayList<SubjectsData>> call = MainActivity.client.remainingsubjects(studentIdReq);
        call.enqueue(new Callback<ArrayList<SubjectsData>>()
        {
            @Override
            public void onResponse(Call<ArrayList<SubjectsData>> call, Response<ArrayList<SubjectsData>> response)
            {
                if (response.isSuccessful())
                {
                    remainingSubjectsDataArrayList = new ArrayList<>(response.body());
                }else
                {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SubjectsData>> call, Throwable t)
            {
                Log.e("RemainingSubjectsData", t.getMessage());
            }
        });
    }

    public void getAttendance (int studentid)
    {
        StudentIdReq studentIdReq= new StudentIdReq(studentid);
        Call<ArrayList<SubjectsData>> call = MainActivity.client.remainingsubjects(studentIdReq);
        call.enqueue(new Callback<ArrayList<SubjectsData>>()
        {
            @Override
            public void onResponse(Call<ArrayList<SubjectsData>> call, Response<ArrayList<SubjectsData>> response)
            {
                if (response.isSuccessful())
                {
                    attendanceArrayList = new ArrayList<>(response.body());
                }else
                {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SubjectsData>> call, Throwable t)
            {
                Log.e("RemainingSubjectsData", t.getMessage());
            }
        });
    }

}
