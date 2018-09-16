package com.example.administrator.whatiseat.P;

import android.content.Context;
import android.util.Log;

import com.example.administrator.whatiseat.Fragment.IHistoryFragemntView;
import com.example.administrator.whatiseat.M.HistoryFragmentModel;

import java.util.LinkedList;
import java.util.List;

public class HistoryFragmentCompl implements IHistoryPresenter {
    private IHistoryFragemntView iHistoryFragemntView;
    private HistoryFragmentModel historyFragmentModel;
    private static List<String> TagData=new LinkedList<>();
    public HistoryFragmentCompl(IHistoryFragemntView iHistoryFragemntView){
        this.iHistoryFragemntView=iHistoryFragemntView;
        this.historyFragmentModel=new HistoryFragmentModel();
    }
    @Override
    public void getTag() {
        historyFragmentModel.getTagData().putTagsData(new HistoryFragmentModel.Tag_CallBack() {
            @Override
            public void TagOnSuccess(List<String> list) {
                TagData=list;
                setTag(list);
            }
        });
    }
    @Override
    public void getHistroy() {
        historyFragmentModel.getHistroyViewData().putHistroyViewData(new HistoryFragmentModel.HistroyView_CallBack() {
            @Override
            public void HistroyViewOnSuccess(List<String> list) {
                Log.i("dbsize",""+list.size());
                setHistroyView(list);
            }
        });
    }

    @Override
    public void clear() {
        historyFragmentModel.clear();
    }

    @Override
    public void close() {
        historyFragmentModel.close();
    }

    @Override
    public void setHistroyView(List<String> list) {
        iHistoryFragemntView.setHistroyView(list);
    }

    @Override
    public void setTag(List<String> list) {
        iHistoryFragemntView.setTags(list);
    }

    @Override
    public void reSetTag() {
        iHistoryFragemntView.setTags(TagData);
    }

}
