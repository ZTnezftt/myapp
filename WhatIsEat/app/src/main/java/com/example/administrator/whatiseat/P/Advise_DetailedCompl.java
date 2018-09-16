package com.example.administrator.whatiseat.P;

import com.example.administrator.whatiseat.Activity.IAdvise_Detailed;
import com.example.administrator.whatiseat.Bean.Advise_Title;
import com.example.administrator.whatiseat.Bean.Data_DIR_Bean.Title;
import com.example.administrator.whatiseat.M.Advise_DetailedModel;

import java.util.List;

public class Advise_DetailedCompl implements IAdvise_DetailedPresenter {
    private IAdvise_Detailed iAdvise_detailed;
    private Advise_DetailedModel advise_detailedModel;
    public Advise_DetailedCompl(IAdvise_Detailed iAdvise_detailed){
        this.iAdvise_detailed=iAdvise_detailed;
        advise_detailedModel=new Advise_DetailedModel();
    }

    @Override
    public void setAdapter(List<Advise_Title> list) {
        iAdvise_detailed.setAdapter(list);
    }

    @Override
    public void getData(List<String> ids) {
        advise_detailedModel
                .getData(ids)
                .putdata(new Advise_DetailedModel.Data_CallBack() {
                    @Override
                    public void putData(List<Advise_Title> advise_titles) {
                        setAdapter(advise_titles);
                    }

                    @Override
                    public void OnError(String str) {
                        iAdvise_detailed.setToast(str);
                    }
                });
    }
}
