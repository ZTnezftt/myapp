package com.example.administrator.whatiseat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.whatiseat.Bean.Burden;
import com.example.administrator.whatiseat.R;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BurdenAdapter extends RecyclerView.Adapter<BurdenAdapter.BurdenViewHolder> implements AdapterUtil<Burden> {

    private List<Burden> list;
    private Context context;

    @Inject
    public BurdenAdapter(Context context, List<Burden> list){
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public BurdenViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.burdenitem,viewGroup,false);
        BurdenViewHolder burdenViewHolder=new BurdenViewHolder(view);
        return burdenViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BurdenViewHolder burdenViewHolder, int i) {
        Log.i("load","加载了burden"+i);
        burdenViewHolder.t1.setText(list.get(i).getT1());
        burdenViewHolder.t2.setText(list.get(i).getT2());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void addList(List<Burden> list) {
        Log.i("burdenAdapter",list.size()+"");
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public void addItem(Burden burden) {
        list.add(burden);
        this.notifyDataSetChanged();
    }

    @Override
    public void replace(List<Burden> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public class BurdenViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.bi_t1) public TextView t1;
        @BindView(R.id.bi_t2) public TextView t2;
        public BurdenViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
