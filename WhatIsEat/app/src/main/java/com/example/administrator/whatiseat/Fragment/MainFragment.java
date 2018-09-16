package com.example.administrator.whatiseat.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.whatiseat.Activity.Advise_Detailed;
import com.example.administrator.whatiseat.Adapter.TuiJianListAdapter;
import com.example.administrator.whatiseat.Component.DaggerMainFragmentFactroy;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.MainFragmentAdapterModule;
import com.example.administrator.whatiseat.P.MainFragmentCompl;
import com.example.administrator.whatiseat.R;
import com.example.administrator.whatiseat.View.Indicator;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment implements IMainFragmentView {
    static Boolean firstStart = false;//判断是否首次打开页面
    @BindView(R.id.tuijianList) RecyclerView recyclerView;
    @Inject TuiJianListAdapter tuiJianListAdapter;
    @Named("LinearLayoutManager") @Inject RecyclerView.LayoutManager layoutManager;
    MainFragmentCompl mainFragmentCompl = new MainFragmentCompl(this);/*持有p的对象*/
    Unbinder unbinder;/*销毁时解绑*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        Init_Dagger2();
        if (firstStart == false) {
            Log.i("pager", "首次启动程序" + firstStart);
            mainFragmentCompl.getList();
        } else {
            Log.i("pager", "回到程序" + firstStart);
            mainFragmentCompl.SetAdapter();
        }
        return view;
    }

    public void Init_Dagger2() {
        /*
               设置activity里title
         */
        CardView Title = getActivity().findViewById(R.id.cardtitle);
        Title.setVisibility(View.VISIBLE);
        DaggerMainFragmentFactroy.builder().mainFragmentAdapterModule(new MainFragmentAdapterModule(getActivity()))
                .layoutManagerModule(new LayoutManagerModule(getActivity()))
                .build().inject(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tuiJianListAdapter);
        tuiJianListAdapter.CallBack(new TuiJianListAdapter.SetPosition() {
            @Override
            public void get(Indicator indicator, ViewPager viewPager, int position) {
                if (position<1){
                    position=3;
                    viewPager.setCurrentItem(3,false);
                }
                if (position>3){
                    position=1;
                    viewPager.setCurrentItem(1,false);
                }
                indicator.setPosition(position);
            }
        });
        tuiJianListAdapter.Click(new TuiJianListAdapter.PagerClick() {
            @Override
            public void get(String url) {
                Log.i("click","点击了");
                Uri uri=Uri.parse(url);
                Intent intent=new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        /*
        推荐的点击
         */
        tuiJianListAdapter.TuiJianClick(new TuiJianListAdapter.TuiJianClick() {
            @Override
            public void getIds(List<String> ids, String title) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("title",title);
                bundle.putSerializable("ids",(Serializable) ids);
                intent.putExtra("ids",bundle);
                intent.setClass(getContext(),Advise_Detailed.class);
                startActivity(intent);
            }
        });
        /*
        推荐list的点击
         */
        tuiJianListAdapter.LisClick(new TuiJianListAdapter.ListClick() {
            @Override
            public void getIds(List<String> ids, String title) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("title",title);
                bundle.putSerializable("ids",(Serializable) ids);
                intent.putExtra("ids",bundle);
                intent.setClass(getContext(),Advise_Detailed.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tuiJianListAdapter=null;
        mainFragmentCompl=null;
        if (firstStart == false) {
            firstStart = true;
        }
        unbinder.unbind();
    }

    @Override
    public void Click_Titile() {

    }

    @Override
    public void SetAdapter(List list) {
        tuiJianListAdapter.replace(list);
    }

    @Override
    public void setToast(String str) {
        Toast(str);
    }

    @Override
    public LifecycleTransformer rxlifecycle() {
        LifecycleTransformer lifecycleTransformer = bindToLifecycle();
        return lifecycleTransformer;
    }

}
