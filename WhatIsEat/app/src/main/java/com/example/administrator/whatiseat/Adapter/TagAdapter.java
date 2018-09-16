package com.example.administrator.whatiseat.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.whatiseat.R;
import com.zhy.view.flowlayout.FlowLayout;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public class TagAdapter extends com.zhy.view.flowlayout.TagAdapter implements AdapterUtil {
    private Context context;
    public TagAdapter(List datas, Context context) {
        super(datas);
        this.context=context;
    }
    @Override
    public View getView(FlowLayout parent, int position, Object o) {
        View view=LayoutInflater.from(context).inflate(R.layout.tagitem,parent,false);
        TextView textView=view.findViewById(R.id.tagtext);
        textView.setText((String)o);
        return view;
    }

    @Override
    public void addList(List list) {

            /*Class c=this.getClass();
            Field[] fields=c.getFields();
            for (Field f : fields) {
                Log.i("f",f.getName());
            }*/
           /* Field f=c.getDeclaredField("mTagDatas");
            f.setAccessible(true);
            f.set("mTagDatas",list);
            Log.i("反射","成功");*/

        this.notifyDataChanged();
    }

    @Override
    public void addItem(Object o) {

    }

    @Override
    public void replace(List list) {

    }

}
