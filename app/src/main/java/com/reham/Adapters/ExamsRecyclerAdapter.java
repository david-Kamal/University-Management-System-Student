package com.reham.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reham.modernacademystudent.R;
import com.reham.modules.CommonModule;
import com.reham.modules.Data.ExamData;
import com.reham.modules.Data.SubjectsData;

import java.util.ArrayList;

public class ExamsRecyclerAdapter extends RecyclerView.Adapter<ExamsRecyclerAdapter.ViewHolder> {

private ArrayList<ExamData> values;
private TextView tv_subject_name, tv_subject_date;
public ExamsRecyclerAdapter()
        {
        this.values = CommonModule.getExamDataArrayList();
        }

@NonNull
@Override
public ExamsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_exam, parent,false);
        return new ExamsRecyclerAdapter.ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull final ExamsRecyclerAdapter.ViewHolder holder, final int position)
        {
            String subjectName = "";
            for (SubjectsData d : CommonModule.getSubjectsDataArrayList())
            {
                if (d.getId() == values.get(position).getSubjectid())
                {
                    subjectName = d.getSubjectname();
                }
            }
            tv_subject_date.setText(String.valueOf(values.get(position).getDate()));
            tv_subject_name.setText(String.valueOf(subjectName));
        }

@Override
public int getItemCount()
{
        return values.size();
}

@Override
public long getItemId(int position) {
        return position;
        }

@Override
public int getItemViewType(int position) {
        return position;
        }


public class ViewHolder extends RecyclerView.ViewHolder {


    public ViewHolder(View itemView)
    {
        super(itemView);
        tv_subject_name = itemView.findViewById(R.id.tv_subject_name);
        tv_subject_date = itemView.findViewById(R.id.tv_subject_date);
    }
}

}
