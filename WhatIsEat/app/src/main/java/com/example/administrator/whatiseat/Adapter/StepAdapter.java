package com.example.administrator.whatiseat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.Step;
import com.example.administrator.whatiseat.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> implements AdapterUtil<Step> {
    private Context context;
    private List<Step> list;
    public StepAdapter(Context context, List<Step> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.stepsitem,viewGroup,false);
        StepViewHolder stepViewHolder=new StepViewHolder(view);
        return stepViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder stepViewHolder, int i) {
        Log.i("load","加载了step"+i);
        stepViewHolder.text.setText(list.get(i).getStep());
        Glide.with(context).load(list.get(i).getImg()).into(stepViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void addList(List<Step> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public void addItem(Step step) {
        list.add(step);
        notifyDataSetChanged();
    }

    @Override
    public void replace(List<Step> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.si_text) TextView text;
        @BindView(R.id.si_img) ImageView img;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
