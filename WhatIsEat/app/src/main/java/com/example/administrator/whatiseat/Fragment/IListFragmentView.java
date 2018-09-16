package com.example.administrator.whatiseat.Fragment;

import com.example.administrator.whatiseat.Bean.Dir_Show_List;

import java.util.List;

public interface IListFragmentView {
    public String putId();
    public String putMenu();
    public String putIndex();
    public void addList(List<Dir_Show_List> lists);
    public void setIndex(int i);
    public void updataList(List<Dir_Show_List> lists);
    public void setDataNum(Boolean b);
    public Boolean putDataNum();
    public void setToast(String str);
}
