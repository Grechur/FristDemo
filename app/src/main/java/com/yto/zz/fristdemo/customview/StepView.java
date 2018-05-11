package com.yto.zz.fristdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yto.zz.fristdemo.R;

/**
 * Created by zz on 2018/5/8.
 */

public class StepView extends View{

    private int mOuterColor;//大弧形颜色
    private int mInnerColor;//小弧形颜色
    private int mBroderWidth = 6;//弧形边宽度
    private int mStepTextColor;//文字颜色
    private int mStepTextSize = 15;//文字大小
    private int mStepMax = 100;//最大步数
    private int mStepCurrent = 20;//当前步数

    private Paint mOuterPaint,mInnerPaint,mStepTextPaint;//画笔

    public StepView(Context context) {
        this(context,null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //拿到自定义的attrs信息
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyStep);
        mOuterColor = array.getColor(R.styleable.MyStep_outerColor, Color.BLUE);
        mInnerColor = array.getColor(R.styleable.MyStep_innerColor,Color.RED);
        mBroderWidth = array.getDimensionPixelSize(R.styleable.MyStep_broderWidth,mBroderWidth);
        mStepTextColor = array.getColor(R.styleable.MyStep_stepTextColor,Color.RED);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.MyStep_stepTextSize,mStepTextSize);
        mStepMax = array.getInteger(R.styleable.MyStep_stepMax,mStepMax);
        mStepCurrent = array.getInteger(R.styleable.MyStep_stepCurrent,mStepCurrent);
        //新建画笔
        mOuterPaint = getPaintByColor(mOuterColor);
        mInnerPaint = getPaintByColor(mInnerColor);
        mStepTextPaint = new Paint();
        mStepTextPaint.setAntiAlias(true);
        mStepTextPaint.setColor(mStepTextColor);
        mStepTextPaint.setTextSize(mStepTextSize);
        array.recycle();
    }

    /**
     * 通过颜色得到画笔
     * @param color
     * @return
     */
    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mBroderWidth);
        paint.setColor(color);
        paint.setStrokeCap(Paint.Cap.ROUND);
        return paint;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //比较宽高，最小值为控件大小
        int size = width>height?height:width;
        setMeasuredDimension(size,size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获得弧形的范围
        RectF rectF = new RectF(mBroderWidth/2,mBroderWidth/2,getWidth() - mBroderWidth/2,getHeight()- mBroderWidth/2);
        //不考虑性能优化
//        canvas.drawArc(rectF,135,270,false,mOuterPaint);
        //分母不能为0
        if (mStepMax == 0) return;
        //计算占比
        float present = (float) mStepCurrent / mStepMax;
        //计算转过的角度
        float sweep = present*270;
        //考虑性能画大弧形
        canvas.drawArc(rectF,135+sweep,270 - sweep,false,mOuterPaint);
        //画小弧形
        canvas.drawArc(rectF,135,sweep,false,mInnerPaint);

        Rect textBound = new Rect();
        String text = mStepCurrent + "";
        //通过画笔计算文字的范围
        mStepTextPaint.getTextBounds(text,0,text.length(),textBound);
        //横轴开始
        int dx = getWidth()/2 - textBound.width()/2;

        int dy = getHeight()/2 - textBound.height()/2;
        Paint.FontMetricsInt fontMetricsInt = mStepTextPaint.getFontMetricsInt();
        //文字的基准点
        int baseLine = (fontMetricsInt.bottom - fontMetricsInt.top)/2 - fontMetricsInt.bottom + dy;
        canvas.drawText(text,dx,baseLine,mStepTextPaint);
    }
}
