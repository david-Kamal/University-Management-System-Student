package com.reham.modernacademystudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.reham.Adapters.ExamsRecyclerAdapter;
import com.reham.modules.CommonModule;
import com.reham.modules.Data.ExamData;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity {

    RecyclerView recycler_exams;
    TextView tv_error;
    CommonModule commonModule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        commonModule = new CommonModule();
        recycler_exams = findViewById(R.id.recycler_exams);
        tv_error = findViewById(R.id.tv_error);
        if (CommonModule.getExamDataArrayList().size() != 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recycler_exams.setLayoutManager(layoutManager);
            ExamsRecyclerAdapter examsRecyclerAdapter = new ExamsRecyclerAdapter();
            recycler_exams.setAdapter(examsRecyclerAdapter);
        }else
        {
            tv_error.setText("لم يتم تسجيل مواد");
        }
    }



}
