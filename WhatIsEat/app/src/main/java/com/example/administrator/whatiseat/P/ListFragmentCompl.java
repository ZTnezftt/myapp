package com.example.administrator.whatiseat.P;

import android.util.Log;

import com.example.administrator.whatiseat.Bean.Dir_Show_List;
import com.example.administrator.whatiseat.Fragment.IListFragmentView;
import com.example.administrator.whatiseat.M.ListFragmentModel;

import java.util.List;

public class ListFragmentCompl implements IListFragmentPresenter {
    private IListFragmentView iListFragmentView;
    private ListFragmentModel listFragmentModel;
    public ListFragmentCompl(IListFragmentView iListFragmentView){
        this.iListFragmentView=iListFragmentView;//v
        this.listFragmentModel=new ListFragmentModel();//m
    }
    @Override
    public void getDataForId() {
        listFragmentModel.getDataForId(getId(),getIndex());
        listFragmentModel.put(new ListFragmentModel.PutData() {
            @Override
            public void putData(List<Dir_Show_List> lists, int size) {
                Log.i("size",size+"");
                if (size==0){
                    iListFragmentView.setDataNum(false);
                }
                iListFragmentView.addList(lists);
                iListFragmentView.setIndex(size);
            }

            @Override
            public void OnError(String str) {
                iListFragmentView.setToast(str);
            }
        });
    }

    @Override
    public void upDataForId() {
        if (iListFragmentView.putDataNum()==false){
            iListFragmentView.setToast("已经到底了~，看看其他的吧");
            return;
        }
        listFragmentModel.getDataForId(getId(),getIndex());
        listFragmentModel.put(new ListFragmentModel.PutData() {
            @Override
            public void putData(List<Dir_Show_List> lists, int size) {
                Log.i("size",size+"");
                if (size==0){
                    iListFragmentView.setDataNum(false);
                }
                iListFragmentView.updataList(lists);
                iListFragmentView.setIndex(size);
            }

            @Override
            public void OnError(String str) {
                iListFragmentView.setToast(str);
            }
        });
    }

    @Override
    public String getId() {
        return iListFragmentView.putId();
    }

    @Override
    public String getIndex() {
        return iListFragmentView.putIndex();
    }

    @Override
    public void getDataForMenu() {
        listFragmentModel.getDataForMenu(getMenu(),getIndex());
        listFragmentModel.put(new ListFragmentModel.PutData() {
            @Override
            public void putData(List<Dir_Show_List> lists, int size) {
                Log.i("size",size+"");
                if (size==0){
                    iListFragmentView.setDataNum(false);
                }
                iListFragmentView.addList(lists);
                iListFragmentView.setIndex(size);
            }

            @Override
            public void OnError(String str) {
                iListFragmentView.setToast(str);
            }
        });
    }

    @Override
    public void upDataForMenu() {
        if (iListFragmentView.putDataNum()==false){
            iListFragmentView.setToast("已经到底了~，看看其他的吧");
            return;
        }
        listFragmentModel.getDataForMenu(getMenu(),getIndex());
        listFragmentModel.put(new ListFragmentModel.PutData() {
            @Override
            public void putData(List<Dir_Show_List> lists, int size) {
                Log.i("size",size+"");
                if (size==0){
                    iListFragmentView.setDataNum(false);
                }
                iListFragmentView.updataList(lists);
                iListFragmentView.setIndex(size);
            }

            @Override
            public void OnError(String str) {
                iListFragmentView.setToast(str);
            }
        });
    }

    @Override
    public String getMenu() {
        return iListFragmentView.putMenu();
    }

}
