package com.example.administrator.whatiseat.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.whatiseat.Activity.ScActivity;
import com.example.administrator.whatiseat.Adapter.MoreItemAdapter;
import com.example.administrator.whatiseat.Bean.MoreItem;
import com.example.administrator.whatiseat.Component.DaggerMoreFragmentFactory;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.MoreAdapterModule;
import com.example.administrator.whatiseat.R;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreFragment extends Fragment {
    @Inject MoreItemAdapter moreItemAdapter;
    @Inject @Named("LinearLayoutManager") RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.morerv) RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.morefragment,container,false);
        ButterKnife.bind(this,view);
        CardView Title=getActivity().findViewById(R.id.cardtitle);
        Title.setVisibility(View.GONE);
        initDagger2();
        recyclerView.setLayoutManager(layoutManager);
        set();
        recyclerView.setAdapter(moreItemAdapter);
        return view;
    }
    public void initDagger2(){
        DaggerMoreFragmentFactory.builder()
                .layoutManagerModule(new LayoutManagerModule(getContext()))
                .moreAdapterModule(new MoreAdapterModule(getContext()))
                .build().inject(this);
        moreItemAdapter.callback(new MoreItemAdapter.ItemClick() {
            @Override
            public void click(int i) {
                switch (i){
                    case 0:
                        Intent intent=new Intent();
                        intent.setClass(getContext(), ScActivity.class);
                        startActivity(intent);
                        break;
                    case 1:

                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }
    public void set(){
        MoreItem moreItem=new MoreItem();
        moreItem.icon=R.drawable.sc;
        moreItem.text="我的收藏";
        moreItemAdapter.addItem(moreItem);
    }
}
