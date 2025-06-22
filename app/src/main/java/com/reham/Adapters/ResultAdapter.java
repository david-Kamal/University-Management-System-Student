package com.reham.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reham.modernacademystudent.R;
import com.reham.modules.Data.ResultData;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private ArrayList<ResultData> data = new ArrayList<>();
    private Context context;
    private TextView tv_result;
    private TextView tv_subject_name;


    public ResultAdapter(ArrayList<ResultData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_result_date, parent,false);
        return new ResultAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        tv_subject_name.setText(String.valueOf(data.get(position).getSubjectname()));
        tv_result.setText(String.valueOf(data.get(position).getResult()));
    }

    @Override
    public int getItemCount()
    {
        // 3dad el hyzhrhum
        return data.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    // awel ma t3ml el adapter class a3ml b3deh 3la tol el view holder
    /// / mn esmu da el class el by manage el view el enta 3mltu
    public class ViewHolder extends RecyclerView.ViewHolder {

        // enta 3mlt custom view ?
        // msh fhmk any custom view l2ah? el htzhr feh data  lbw ta3t el result shklha
        // l2 lsa tab e3mlu asdk a7ot al ltextview bt3
        public ViewHolder(View itemView)
        {
            super(itemView);
            tv_result = itemView.findViewById(R.id.tv_result);
            tv_subject_name= itemView.findViewById(R.id.tv_subject_name);
//            itemView.setOnClickListener(v -> {
//
//                clientPosition = getAdapterPosition();
//
//                revisionApplicationSearchDialog.setTexts(clientPosition);
//
//                revisionApplicationSearchDialog.dismiss();
//            });

        }
    }
}
// hello baby heloooo   ana k o nt b3ml fl recyclerview toyp t3ala nshufu ok