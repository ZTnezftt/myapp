package com.example.administrator.whatiseat.P;

import java.util.List;

public interface IHistoryPresenter {
    public void setTag(List<String> list);
    public void reSetTag();
    public void getTag();
    public void setHistroyView(List<String> list);
    public void getHistroy();
    public void clear();
    public void close();
}
