package com.example.administrator.whatiseat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.whatiseat.Bean.Scitem;
import com.example.administrator.whatiseat.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScAdapter extends RecyclerView.Adapter<ScAdapter.ScHolder>  implements AdapterUtil<Scitem> {
    private Context context;
    private List<Scitem> list;
    private OnClick onClick;
    public ScAdapter(Context context,List<Scitem> scitems){
        this.context=context;
        this.list=scitems;
    }
    @NonNull
    @Override
    public ScHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.scrvitem,viewGroup,false);
        return new ScHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScHolder scHolder, final int i) {
        Glide.with(context).load(list.get(i).urls).into(scHolder.albums);
        scHolder.textView.setText(list.get(i).title);
        scHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.Click(list.get(i).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void addList(List<Scitem> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public void addItem(Scitem scitem) {
        this.list.add(scitem);
        this.notifyDataSetChanged();
    }

    @Override
    public void replace(List<Scitem> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }
    public void CallBack(OnClick onClick){
        this.onClick=onClick;
    }
    class ScHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.scitemimg) public ImageView albums;
        @BindView(R.id.scitemtv) public TextView textView;
        @BindView(R.id.scitem) public RelativeLayout layout;
        public ScHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface OnClick{
        public void Click(String id);
    }
}
