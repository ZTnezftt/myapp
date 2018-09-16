package com.example.administrator.whatiseat.Activity;

import com.example.administrator.whatiseat.Bean.Advise_Title;
import com.example.administrator.whatiseat.Bean.Data_DIR_Bean.Title;

import java.util.List;

public interface IAdvise_Detailed {
    public void setAdapter(List<Advise_Title> list);//
    public void setToast(String str);
}
