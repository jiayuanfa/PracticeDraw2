package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        // 设置拐角为圆角
        PathEffect cornerPathEffect = new CornerPathEffect(20);
        paint.setPathEffect(cornerPathEffect);
        canvas.drawPath(path, paint);
        canvas.save();
        canvas.translate(500, 0);

        // 第二处：DiscretePathEffect
        // 设置抖动弯曲效果
        PathEffect discreatePathEffect = new DiscretePathEffect(20,5);
        paint.setPathEffect(discreatePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);

        // 第三处：DashPathEffect
        // 设置断线的效果 数组是虚线的格式 下面的20 10 5 10 的意思就是画20像素间隔10个像素画5个像素间隔10个像素 第二个参数是虚线的偏移量
        PathEffect dashPathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);

        // 第四处：PathDashPathEffect
        // 使用一个Path来绘制虚线
        Path dashPath = new Path();
        dashPath.lineTo(20, -30);
        dashPath.lineTo(40, 0);
        dashPath.close();
        PathEffect pathDashPathEffect = new PathDashPathEffect(dashPath, 50, 0, PathDashPathEffect.Style.MORPH);
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);

        // 第五处：SumPathEffect
        // 两种PathEffect结合
        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
        PathEffect pathEffect = new SumPathEffect(dashEffect, discreteEffect);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);

        // 第六处：ComposePathEffect
        // 组合效果 前半部分使用第一个PathEffect 后半部分使用第二个PathEffect
        PathEffect dashEffect1 = new DashPathEffect(new float[]{20, 10}, 0);
        PathEffect discreteEffect1 = new DiscretePathEffect(20, 5);
        PathEffect pathEffect1 = new ComposePathEffect(dashEffect1, discreteEffect1);
        paint.setPathEffect(pathEffect1);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
