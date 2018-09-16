package com.example.administrator.whatiseat.P;


import com.example.administrator.whatiseat.Bean.Advise_Title;
import com.example.administrator.whatiseat.Bean.Data_DIR_Bean.Title;

import java.util.List;

public interface IAdvise_DetailedPresenter {
    public void setAdapter(List<Advise_Title> list);

    /**
     *
     * @param ids 传递的id集合
     */
    public void getData(List<String> ids);
}
