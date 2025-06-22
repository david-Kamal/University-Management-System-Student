package com.reham.modernacademystudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.common.internal.service.Common;
import com.reham.Adapters.RemainingSubjectsAdapter;
import com.reham.modules.CommonModule;
import com.reham.modules.Data.SubjectsData;

import java.util.ArrayList;

public class RemainingActivity extends AppCompatActivity {

    RecyclerView recycler_subjects;
    ArrayList<SubjectsData> subjectsData;
    CommonModule commonModule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remaining);
        subjectsData = CommonModule.getRemainingSubjectsDataArrayList();
        System.out.println(subjectsData.size());
        recycler_subjects = findViewById(R.id.recycler_subjects);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_subjects.setLayoutManager(layoutManager);
        RemainingSubjectsAdapter adapter = new RemainingSubjectsAdapter(subjectsData);
        recycler_subjects.setAdapter(adapter);
    }



}
