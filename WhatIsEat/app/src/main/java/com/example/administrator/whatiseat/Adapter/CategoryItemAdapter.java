package com.example.administrator.whatiseat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.whatiseat.DB_List.Categroy.Items;
import com.example.administrator.whatiseat.R;

import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.MyVH> {
    private List<Items> list;
    private Context context;
    private ItemClick itemClick;
    public CategoryItemAdapter(Context context, List<Items> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.categoryitem,viewGroup,false);
        MyVH myVH=new MyVH(view);
        return myVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH myVH, final int i) {
        myVH.textView.setText(list.get(i).getName());
            myVH.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.getPositionId(list.get(i).getId());
                }
            });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClick(ItemClick itemClick){
        this.itemClick=itemClick;
    }
    public interface ItemClick{
        void getPositionId(String id);
    }
    class MyVH extends RecyclerView.ViewHolder{
        TextView textView;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.categoryitemtext);
        }
    }
}
