package com.example.k.myapplication;

/**
 * Created by k on 2016/7/28.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.k.myapplication.Util.dp_vs_px;

/**
 * Created by k on 2016/5/30.
 */
public class MyView extends View {
    int right;
    WindowManager wm = (WindowManager) getContext()
            .getSystemService(Context.WINDOW_SERVICE);
    //得到屏幕的参数
     int width = wm.getDefaultDisplay().getWidth();
    //int height = wm.getDefaultDisplay().getHeight();
    Context context;

    public void method(int x, int height) {
        if (x <= 300) {
            //$$$$$$$$$$$
            //重要方法，直接在父布局里修改子布局的位置,第一个和第二个参数组成矩形的左上角x坐标，剩下两个组成右下角y坐标
            layout(dp_vs_px.dp2px(5), height - x, width-dp_vs_px.dp2px(5), height + dp_vs_px.dp2px(50) - x);
            //$$$$$$$$$$$
            return;
        }
        //注意:当用getLeft()+dp_vs_px.dp2px(5)这样的方式，显示将会出现意料之外的效果（异常），因为当getLeft（）是得到上次位置，这样一来，就会循环减下去
        layout(dp_vs_px.dp2px(5), height - dp_vs_px.dp2px(150) + (x - dp_vs_px.dp2px(150)),width-dp_vs_px.dp2px(5) , height + dp_vs_px.dp2px(50) - dp_vs_px.dp2px(150) + (x - dp_vs_px.dp2px(150)));
    }

    private static final int DEFAULT_VIEW_WIDTH = 100;
    private static final int DEFAULT_VIEW_HEIGHT = 100;

    public MyView(Context context)

    {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    //测量自定义View的宽和高，数据来源于xml文件
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMesureSpec) {
        super.onMeasure(widthMeasureSpec, heightMesureSpec);
        int width = measureDimension(DEFAULT_VIEW_WIDTH, widthMeasureSpec);
        int height = measureDimension(DEFAULT_VIEW_HEIGHT, heightMesureSpec);
        setMeasuredDimension(width, height);
    }

    protected int measureDimension(int defaultSize, int measureSpec) {

        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //当子控件被width或者height被设置为确定值时就会被激活
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize; //建议：result直接使用确定值
        }
        //当子控件被设置为wrap_content时，就会进入此判断
        else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.max(defaultSize, specSize); //建议：result不能大于specSize
        }
        //UNSPECIFIED,没有任何限制，所以可以设置任何大小
        //多半出现在自定义的父控件的情况下，期望由自控件自行决定大小
        else {
            result = defaultSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(dp_vs_px.dp2px(20));
        paint.setAntiAlias(true);
        //设置文字的重心点，比如canvas.drawText()第2个参数为宽的一半，那么重心点就会落在d上，从而就可以实现了水平居中
        paint.setTextAlign(Paint.Align.CENTER);
        //获得字体的质量
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
// 计算文字高度
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
// 计算文字baseline
        //200为本控件的高度
        float textBaseY = dp_vs_px.dp2px(50) - (dp_vs_px.dp2px(50) - fontHeight) / 2 - fontMetrics.bottom;
        canvas.drawText("相册", width / 2, textBaseY, paint);

    }
}