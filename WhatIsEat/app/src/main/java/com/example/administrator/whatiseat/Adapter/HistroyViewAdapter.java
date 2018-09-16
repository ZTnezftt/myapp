package com.example.administrator.whatiseat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.whatiseat.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistroyViewAdapter extends RecyclerView.Adapter<HistroyViewAdapter.MyViewHolder> implements AdapterUtil<String> {
    private List<String> list;
    private CallBack callBack;
    private Context context;
    public HistroyViewAdapter(Context context,List<String> list){
        Log.i("su",list.size()+"---------------------");
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.histroyitem,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Log.i("bind","load"+i);
        myViewHolder.itemText.setText(list.get(i));
        myViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClick(list.get(i),i);
            }
        });
    }
    @Override
    public int getItemCount() {
        Log.i("adapter",list.size()+"");
        return list.size();
    }

    @Override
    public void addList(List<String> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public void addItem(String s) {
        this.list.add(s);
        this.notifyDataSetChanged();
    }

    @Override
    public void replace(List<String> list) {
        Log.i("adapter",list.size()+"");
        this.list.clear();
        this.list.addAll(list);
        Log.i("afteradapter",this.list.size()+"");
        this.notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.hf_item) TextView itemText;
        @BindView(R.id.hf_rl) RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this.itemView);
            itemText=itemView.findViewById(R.id.hf_item);
            relativeLayout=itemView.findViewById(R.id.hf_rl);
            //itemText=itemView.findViewById(R.id.hf_item);
        }
    }

    private void setClick(String s,int i){
        callBack.OnItemClick(s,i);
    }

    public void getClick(CallBack callBack){
        this.callBack=callBack;
    }
    public interface CallBack{
        public void OnItemClick(String s,int i);
    }
}
