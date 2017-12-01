package com.neu.neuer.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

/**
 * Created by fengyuluo on 2017/11/20.
 */

public class RoundImageView extends AppCompatImageView {
    private Paint paint;
    public RoundImageView(Context context) {
        this(context,null);
    }
    public RoundImageView(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }
    public RoundImageView(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
        paint = new Paint();
    }
    /**
     * 绘制圆角矩形图片
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if(null!=drawable){
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap b = getRoundBitmap(bitmap,20);
            final Rect rectsrc = new Rect(0,0,b.getWidth(),b.getHeight());
            final Rect rectDext = new Rect(0,0,getWidth(),getHeight());
            paint.reset();
            canvas.drawBitmap(b,rectsrc,rectDext,paint);
        }
        else{
            super.onDraw(canvas);
        }
    }

    /**
     * 获取圆角矩形
     * @param bitmap
     * @param roundPx
     * @return
     */
    private Bitmap getRoundBitmap(Bitmap bitmap, int roundPx){
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        canvas.drawARGB(0,0,0,0);
        paint.setColor(color);
        int x = bitmap.getWidth();
        canvas.drawRoundRect(rectF,roundPx,roundPx,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,rect,rect,paint);
        return output;
    }
}
