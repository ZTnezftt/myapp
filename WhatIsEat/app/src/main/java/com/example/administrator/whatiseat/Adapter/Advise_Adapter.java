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
import com.example.administrator.whatiseat.Bean.Advise_Title;
import com.example.administrator.whatiseat.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Advise_Adapter extends RecyclerView.Adapter<Advise_Adapter.AdviseHolder> implements AdapterUtil<Advise_Title> {
    private List<Advise_Title> list;
    private Context context;
    private Callback_Click callback_click;
    public Advise_Adapter(List<Advise_Title> list, Context context){
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public AdviseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.advise_list_item,viewGroup,false);
        return new AdviseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdviseHolder adviseHolder, final int i) {
        Glide.with(context).load(list.get(i).albums).into(adviseHolder.albums);
        adviseHolder.imtro.setText(list.get(i).imtro);
        adviseHolder.title.setText(list.get(i).title);
        adviseHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback_click.layoutClick(list.get(i).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void addList(List<Advise_Title> list) {

    }

    @Override
    public void addItem(Advise_Title advise_title) {

    }

    @Override
    public void replace(List<Advise_Title> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }
    public void layoutClick(Callback_Click callback_click){
        this.callback_click=callback_click;
    }
    public interface Callback_Click{
        public void layoutClick(String id);
    }
    public class AdviseHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.advise_imgae) ImageView albums;
        @BindView(R.id.advise_texttitle) TextView title;
        @BindView(R.id.advise_textimtro) TextView imtro;
        @BindView(R.id.advise_layout) RelativeLayout layout;
        public AdviseHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
