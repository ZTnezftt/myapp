package com.example.administrator.whatiseat.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.whatiseat.Activity.MyApplication;
import com.example.administrator.whatiseat.Activity.SearchView;
import com.example.administrator.whatiseat.Adapter.HistroyViewAdapter;
import com.example.administrator.whatiseat.Adapter.TagAdapter;
import com.example.administrator.whatiseat.Component.DaggerHistroyFactory;
import com.example.administrator.whatiseat.Module.HistroyViewModule;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.TagModule;
import com.example.administrator.whatiseat.P.HistoryFragmentCompl;
import com.example.administrator.whatiseat.P.MainFragmentCompl;
import com.example.administrator.whatiseat.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryFragment extends Fragment implements IHistoryFragemntView {
    @BindView(R.id.id_flowlayout) TagFlowLayout flowLayout;
    @BindView(R.id.hf_histroy) RecyclerView histroyView;
    @BindView(R.id.hf_clear) TextView clear;
    @Named("LinearLayoutManager") @Inject RecyclerView.LayoutManager layoutManager;
    @Inject HistroyViewAdapter histroyViewAdapter;
    @Inject TagAdapter tagAdapter;
    HistoryFragmentCompl historyFragmentCompl=new HistoryFragmentCompl(this);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.historyfragment,container,false);
        ButterKnife.bind(this,view);
        Init_Dagger2();
        ///////////////////////////////////////////////////////////////////////////////////////////
        historyFragmentCompl.getTag();//m->p->v.setTag()从model获取数据并调用v的gettag设置tagview
        historyFragmentCompl.getHistroy();
        return view;
    }
    private void Init_Dagger2(){
        DaggerHistroyFactory.builder()
                .layoutManagerModule(new LayoutManagerModule(MyApplication.getInstance()))
                .histroyViewModule(new HistroyViewModule(MyApplication.getInstance()))
                .tagModule(new TagModule(getActivity()))
                .build().inject(this);
        histroyView.setLayoutManager(layoutManager);
        histroyView.setAdapter(histroyViewAdapter);
        flowLayout.setAdapter(tagAdapter);
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                EditText Search =getActivity().findViewById(R.id.Dir_Search);
                Search.setText(getTagValue(position));
                SearchView searchView=(SearchView)getActivity();
                searchView.toDirShow();
                return true;
            }
        });
        histroyViewAdapter.getClick(new HistroyViewAdapter.CallBack() {
            @Override
            public void OnItemClick(String s, int i) {
                EditText Search =getActivity().findViewById(R.id.Dir_Search);
                Search.setText(s);
                SearchView searchView=(SearchView)getActivity();
                searchView.toDirShow();
            }
        });
    }

    @Override
    public void setTags(List<String> list) {
        Class className=tagAdapter.getClass();
        className=className.getSuperclass();
        try {
            Field field=className.getDeclaredField("mTagDatas");
            field.setAccessible(true);
            field.set(tagAdapter,list);
            tagAdapter.notifyDataChanged();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getTagValue(int position){
        String str="";
        Class className=tagAdapter.getClass();
        className=className.getSuperclass();
        Field field= null;
        try {
            field = className.getDeclaredField("mTagDatas");
            field.setAccessible(true);
            List<String> list= (List<String>) field.get(tagAdapter);
            str=list.get(position);
            Log.i("str",str);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return str;
    }
    @Override
    public void setHistroyView(List<String> list) {
        Log.i("v",list.size()+"");
        histroyViewAdapter.replace(list);
    }

    @OnClick(R.id.hf_clear)
    @Override
    public void clear(){
        historyFragmentCompl.clear();
        histroyViewAdapter.replace(new LinkedList<String>());
    }

    @Override
    public void onStop() {
        historyFragmentCompl.close();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        historyFragmentCompl.close();
        super.onDestroy();

    }
}
