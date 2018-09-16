package com.example.administrator.whatiseat.Adapter;



import java.util.List;

public interface AdapterUtil<T>{
    public void addList(List<T> list);
    public void addItem(T t);
    public void replace(List<T> list);
}
