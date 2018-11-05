package com.example.administrator.whatiseat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.whatiseat.Bean.Banner_in;
import com.example.administrator.whatiseat.Bean.TuiJian;
import com.example.administrator.whatiseat.Bean.TuiJianItem;
import com.example.administrator.whatiseat.R;
import com.example.administrator.whatiseat.View.Indicator;

import java.util.LinkedList;
import java.util.List;

public class TuiJianListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterUtil{
    private ViewPager viewPager;
    private ListClick listClick;
    private TuiJianClick tuiJianClick;
    private PagerClick pagerClick;
    private Indicator indicator;
    private Context context;
    private ViewGroup viewGroup;
    private List<T> list;
    private SetPosition setPosition;
    public TuiJianListAdapter(Context context, List<T> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.viewGroup=viewGroup;
        if (i==1){
            View view=LayoutInflater.from(context).inflate(R.layout.mainfragment_head,viewGroup,false);
            return new HeadHolder(view);
        }else if (i==2){
            View view = LayoutInflater.from(context).inflate(R.layout.tuijianitem, viewGroup, false);
            return new ListHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.mainfragmenttuijian, viewGroup, false);
            return new TuiJian(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof ListHolder){
            Log.i("load","tuijian"+i);
            ((ListHolder) viewHolder).textView.setText(getTuiJian(i).getText());
            Glide.with(context).
                    load(getTuiJian(i).getUrls()).
                    into(((ListHolder) viewHolder).imageView);
            ((ListHolder) viewHolder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tuiJianClick.getIds(getTuiJian(i).getIds(), (String) ((ListHolder) viewHolder).textView.getText());
                }
            });
        }else if(viewHolder instanceof HeadHolder){
            Log.i("load","加载头部");
            this.viewPager=((HeadHolder) viewHolder).viewPager;
            this.indicator=((HeadHolder) viewHolder).indicator;
            setBanner(getBanner(0),(HeadHolder) viewHolder);
            ((HeadHolder) viewHolder).viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                @Override
                public void onPageSelected(int position) {
                    setPosition.get(indicator,viewPager,position);
                }
            });
            ((HeadHolder) viewHolder).viewPager.setOnTouchListener(new View.OnTouchListener() {
                int flage = 0 ;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            flage = 0 ;
                            break ;
                        case MotionEvent.ACTION_MOVE:
                            flage = 1 ;
                            break ;
                        case  MotionEvent.ACTION_UP :
                            if (flage == 0) {
                                pagerClick.get(getBanner(0).getBanner().getUrls().get(indicator.get()-1));
                            }
                            break ;
                    }
                    return false;
                }
            });
        }else if(viewHolder instanceof TuiJian){
            ((TuiJian) viewHolder).c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listClick.getIds(getList(i).get(0),"这个季节吃什么？");
                }
            });
            ((TuiJian) viewHolder).c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listClick.getIds(getList(i).get(0),"本周美食推荐！");
                }
            });
        }
    }
    public void setBanner(Banner_in banner,HeadHolder headHolder){
        List<View> pageitem=new LinkedList<>();
        ImageView pageimage;
        for (int i=0;i<banner.getSize()+2;i++){
            View view=LayoutInflater.from(context).inflate(R.layout.pageitem,viewGroup,false);
            pageimage=view.findViewById(R.id.PageImage);
            Glide.with(context).
                    load(banner.getBanner().getBanner().get(i)).
                    into(pageimage);
            pageitem.add(view);
        }
        MainPagerAdapter mainPagerAdapter=new MainPagerAdapter(pageitem);
        headHolder.viewPager.setAdapter(mainPagerAdapter);
        headHolder.viewPager.setCurrentItem(1,false);
        headHolder.indicator.setSize(pageitem.size()-2);
        headHolder.indicator.setPosition(1);
    }
    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof TuiJianItem){
          return 2;
        }else if(list.get(position) instanceof Banner_in){
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public TuiJianItem getTuiJian(int position){
        if (list.get(position) instanceof TuiJianItem){
            return (TuiJianItem) list.get(position);
        }
        return null;
    }
    public Banner_in getBanner(int position){
        if (list.get(position) instanceof Banner_in){
            return (Banner_in) list.get(position);
        }
        return null;
    }
    public List<List<String>> getList(int position){
        if (list.get(position) instanceof List){
            return (List)list.get(position);
        }
        return null;
    }
    public void removeall(){
        list.clear();
    }
    @Override
    public void addList(List list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public void addItem(Object o) {

    }

    @Override
    public void replace(List list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    class ListHolder<T> extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;
        public ListHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.itemcard);
            imageView=itemView.findViewById(R.id.tuijianImage);
            textView=itemView.findViewById(R.id.tuijianText);
        }
    }
    class HeadHolder<T> extends RecyclerView.ViewHolder{
        Indicator indicator;
        ViewPager viewPager;
        public HeadHolder(@NonNull View itemView) {
            super(itemView);
            indicator=itemView.findViewById(R.id.indicator);
            viewPager=itemView.findViewById(R.id.mainpager);
        }
    }
    class TuiJian<T> extends RecyclerView.ViewHolder{
        CardView c1,c2;
        public TuiJian(@NonNull View itemView) {
            super(itemView);
            c1=itemView.findViewById(R.id.mainfragmentc1);
            c2=itemView.findViewById(R.id.mainfragmentc2);
        }
    }
    public void CallBack(SetPosition setPosition){
        this.setPosition=setPosition;
    }

    public interface SetPosition{
        public void get(Indicator indicator,ViewPager viewPager,int position);
    }

    public void Click(PagerClick pagerClick){
        this.pagerClick=pagerClick;
    }

    public interface PagerClick{
        public void get(String url);
    }

    public void TuiJianClick(TuiJianClick tuiJianClick){
        this.tuiJianClick=tuiJianClick;
    }
    public void LisClick(ListClick listClick){
        this.listClick=listClick;
    }
    public interface TuiJianClick{
        void getIds(List<String> ids,String title);
    }
    public interface ListClick{
        void getIds(List<String> ids,String title);
    }
}
