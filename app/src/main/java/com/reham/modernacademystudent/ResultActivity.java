package com.reham.modernacademystudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.reham.Adapters.ResultAdapter;
import com.reham.modules.Data.ResultData;
import com.reham.modules.Data.StudentIdReq;
import com.reham.modules.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView resultrecycler;
    private ResultData resultData;

    ArrayList<ResultData> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//
        resultrecycler = (RecyclerView) findViewById(R.id.resultsRecyclerView);

        // btbos henak leh e7na 7aten data static rk yast a=;D  ahah nseet :(

        try
        {
            getResults();

        }catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void getResults()
    {
        SessionManager sessionManager = new SessionManager(this);
        StudentIdReq studentIdReq = new StudentIdReq(sessionManager.getStudentId());
        Call<ArrayList<ResultData>> call = MainActivity.client.returnResult(studentIdReq);
        call.enqueue(new Callback<ArrayList<ResultData>>() {
            @Override
            public void onResponse(Call<ArrayList<ResultData>> call, Response<ArrayList<ResultData>> response) {
                if(response.isSuccessful())
                {
                    data = response.body();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ResultActivity.this);
                    resultrecycler.setLayoutManager(linearLayoutManager);
                    ResultAdapter resultAdapter = new ResultAdapter(data, ResultActivity.this);
                    resultrecycler.setAdapter(resultAdapter);
                }else
                {
                    Toast.makeText(ResultActivity.this, "Error get results", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResultData>> call, Throwable t)
            {
                Toast.makeText(ResultActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
