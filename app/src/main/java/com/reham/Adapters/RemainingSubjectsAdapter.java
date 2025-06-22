package com.reham.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reham.modernacademystudent.R;
import com.reham.modules.Data.SubjectsData;

import java.util.ArrayList;

public class RemainingSubjectsAdapter extends RecyclerView.Adapter<RemainingSubjectsAdapter.ViewHolder>
{
    private TextView tv_subject_name, tv_subject_date;
    ArrayList<SubjectsData> data;

    public RemainingSubjectsAdapter(ArrayList<SubjectsData> data)
    {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_remaining_subject, parent,false);
        return new RemainingSubjectsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        tv_subject_name.setText(String.valueOf(data.get(position).getSubjectname()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            tv_subject_name = itemView.findViewById(R.id.tv_subject_name);
        }
    }
}
