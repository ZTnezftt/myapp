package com.example.administrator.whatiseat.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.administrator.whatiseat.R;
import com.example.administrator.whatiseat.Util.Util;

public class Indicator extends View {
    private OnPositionChange onPositionChange=new OnPositionChange() {
        @Override
        public void setChange(int i) {
            position=i;
            invalidate();
        }
    };
    private float radius=15;
    private float distance=30;
    private int size=0;
    private int position=0;
    private Paint paint1,paint2;
    private int h,w;
    public Indicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        radius= Util.dpToPx(context,2);
        distance=Util.dpToPx(context,8);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w=w;
        this.h=h;
        /*
          P1 选中的样式
          p2未选中的样式
         */
        paint1=new Paint();
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(5f);
        paint1.setColor(getResources().getColor(R.color.ivory));
        paint2=new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setColor(Color.GRAY);
        paint2.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas){
        if (size==0||position==0||position>size){
            Log.e("In","index or position error");
            return;
        }
        canvas.translate(w/2,h/2);
        canvas.save();
        float offset;
        if (size%2==0){
            offset=(float) (((size/2)-0.5)*radius*2)+(((size/2)-1)*distance+distance/2);
        }else{
            offset=(size/2)*radius*2+(size/2)*distance;
        }
        canvas.translate(-offset,0);
        for (int i=1;i<=size;i++){
            if (i==position){
                RectF rectF = new RectF(-radius*2,-radius,radius*2,radius);
                canvas.drawRoundRect(rectF,30,30,paint1);
            }else{
                canvas.drawCircle(0,0,radius,paint2);
            }
            canvas.translate(radius+distance+radius,0);
        }
        canvas.restore();
    }

    public int get(){
       return position;
    }

    public void setSize(int size){
        this.size=size;
    }

    public void setPosition(int position){
        Log.i("in","设置为"+position);
        onPositionChange.setChange(position);
    }
    class IndexError extends Exception{
        public IndexError(String msg){
            super(msg);
        }
    }

    interface OnPositionChange{
        public void setChange(int i);
    }
}
