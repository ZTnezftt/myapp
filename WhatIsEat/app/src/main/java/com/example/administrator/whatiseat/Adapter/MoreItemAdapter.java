package com.example.administrator.whatiseat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.whatiseat.Bean.MoreItem;
import com.example.administrator.whatiseat.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreItemAdapter extends RecyclerView.Adapter<MoreItemAdapter.MoreItemHolder> implements AdapterUtil<MoreItem> {
    private Context context;
    private List<MoreItem> list;
    private ItemClick itemClick;
    public MoreItemAdapter(Context context, List<MoreItem> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public MoreItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.moreitem,viewGroup,false);
        return new MoreItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreItemHolder moreItemHolder, final int i) {
        moreItemHolder.icon.setBackgroundResource(list.get(i).icon);
        moreItemHolder.text.setText(list.get(i).text);
        moreItemHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.click(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void callback(ItemClick itemClick){
        this.itemClick=itemClick;
    }

    @Override
    public void addList(List<MoreItem> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public void addItem(MoreItem moreItem) {
        this.list.add(moreItem);
        this.notifyDataSetChanged();
    }

    @Override
    public void replace(List<MoreItem> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public interface ItemClick{
        public void click(int i);
    }
    class MoreItemHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.moreitem) RelativeLayout relativeLayout;
        @BindView(R.id.moreicon) ImageView icon;
        @BindView(R.id.moretext) TextView text;
        public MoreItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
