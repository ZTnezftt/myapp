package com.example.administrator.whatiseat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.whatiseat.Bean.Dir_Show_List;
import com.example.administrator.whatiseat.R;

import java.util.List;

public class Dir_Show_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterUtil {
    private Context context;
    private GetId getId;
    private List<Dir_Show_List> dir_show_lists;
    public Dir_Show_Adapter(Context context, List<Dir_Show_List> lists){
        this.dir_show_lists=lists;
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==1) {
            View view = LayoutInflater.from(context).inflate(R.layout.dir_data_item, viewGroup, false);
            ShowHolder showHolder = new ShowHolder(view);
            return showHolder;
        }
        else{
            View view= LayoutInflater.from(context).inflate(R.layout.dir_data_item_advertise,viewGroup,false);
            Advertise advertise=new Advertise(view);
            return advertise;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ShowHolder){
            Log.i("load","加载了show"+i);
            final Dir_Show_List dir_show_list = dir_show_lists.get(i);
            Glide.with(context)
                    .load(dir_show_list.getAlbums())
                    .into(((ShowHolder) viewHolder).albums);
            ((ShowHolder) viewHolder).title.setText(dir_show_list.getTitle());
            ((ShowHolder) viewHolder).ingredients.setText(dir_show_list.getIngredients());
            ((ShowHolder) viewHolder).imtro.setText(dir_show_list.getImtro());
            ((ShowHolder) viewHolder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getId.getId(Integer.valueOf(dir_show_list.getId()),((ShowHolder) viewHolder).albums);
                }
            });
        }else{
            Log.i("Advertise","打印广告");
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (dir_show_lists.get(position).getValue()==0){
            return 0;
        }else{
            return 1;
        }
    }
    @Override
    public int getItemCount() {
        return dir_show_lists.size();
    }

    @Override
    public void addList(List list) {
        this.dir_show_lists.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public void addItem(Object o) {

    }

    @Override
    public void replace(List list) {
        this.dir_show_lists.clear();
        this.dir_show_lists.addAll(list);
        Log.i("size2",""+dir_show_lists.size());
        this.notifyDataSetChanged();
    }

    class ShowHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView albums;
        private TextView title,ingredients,imtro;
        public ShowHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.Dir_Show_item_itemCard);
            albums=itemView.findViewById(R.id.Dir_Show_item_albums);
            title=itemView.findViewById(R.id.Dir_Show_item_title);
            ingredients=itemView.findViewById(R.id.Dir_Show_item_ingredients);
            imtro=itemView.findViewById(R.id.Dir_Show_item_imtro);
        }
    }
    class Advertise extends RecyclerView.ViewHolder{
        private ImageView adv;
        public Advertise(@NonNull View itemView) {
            super(itemView);
            adv=itemView.findViewById(R.id.advertise);
        }
    }
    public interface GetId{
        public void getId(int id,ImageView imageView);
    }
    public void CallBack(GetId getId){
        this.getId=getId;
    }
}
