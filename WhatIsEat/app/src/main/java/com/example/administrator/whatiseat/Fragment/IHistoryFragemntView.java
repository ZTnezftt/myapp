package com.example.administrator.whatiseat.Fragment;

import java.util.List;

public interface IHistoryFragemntView {
    public void setTags(List<String> list);//给tagView添加数据并显示
    public void setHistroyView(List<String> list);//给--添加数据并显示
    public void clear();//清空数据库并设置控件隐藏
    public String getTagValue(int position);//获取tag点击事件的值
}
