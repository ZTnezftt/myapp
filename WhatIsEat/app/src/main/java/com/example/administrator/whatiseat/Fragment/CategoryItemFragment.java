package com.example.administrator.whatiseat.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.whatiseat.Activity.MyApplication;
import com.example.administrator.whatiseat.Activity.SearchView;
import com.example.administrator.whatiseat.Adapter.CategoryItemAdapter;
import com.example.administrator.whatiseat.DB_List.Categroy.Items;
import com.example.administrator.whatiseat.R;

import java.util.List;

public class CategoryItemFragment extends BaseFragment {
    private String title;
    private List<Items> list;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.categoryitemrecyclerview,container,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        CategoryItemAdapter categoryItemAdapter=new CategoryItemAdapter(getContext(),list);
        recyclerView=view.findViewById(R.id.categoryitem);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoryItemAdapter);
        categoryItemAdapter.setOnItemClick(new CategoryItemAdapter.ItemClick() {
            @Override
            public void getPositionId(String id) {
                Log.i("Cid",id);
                Intent intent=new Intent(getActivity(), SearchView.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        return view;
    }
    public void setTitle(String t){
        this.title=t;
    }

    public void setList(List<Items>list){
        this.list=list;
    }

    interface putId{
        void getId(String id);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
