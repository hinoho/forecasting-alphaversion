package com.revolve44.fragments22.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.Nullable;

public class GraphView extends View {
    int x = 80;
    int y = 40;
    Paint paint = new Paint();
    Path path = new Path();
    LinkedHashMap<Long, Float> dataMap;
    CornerPathEffect cornerPathEffect = new CornerPathEffect(5);
    float nominalPower;

    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setPathEffect(cornerPathEffect);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawLine(x, y, x,200 + y,paint);
        canvas.drawLine(x,200 + y,700 + x,200 + y,paint);

        paint.setStrokeWidth(1);
        canvas.drawLine(x, 50 + y,700 + x,50 + y, paint);
        canvas.drawLine(x, 100 + y,700 + x,100 + y, paint);
        canvas.drawLine(x, 150 + y,700 + x,150 + y, paint);

        canvas.drawLine(100 + x, y, 100 + x,200 + y, paint);
        canvas.drawLine(200 + x, y, 200 + x,200 + y, paint);
        canvas.drawLine(300 + x, y, 300 + x,200 + y, paint);
        canvas.drawLine(400 + x, y, 400 + x,200 + y, paint);
        canvas.drawLine(500 + x, y, 500 + x,200 + y, paint);
        canvas.drawLine(600 + x, y, 600 + x,200 + y, paint);

        if(dataMap != null) {
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(20);
            int i = 0;

            String pattern = "HH:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            for (Map.Entry<Long, Float> entry : dataMap.entrySet()){
                canvas.drawText(simpleDateFormat.format(new Date(entry.getKey())),  i * 100 + 55, y + 225, paint);
                if(i == 0){
                    path.moveTo(0, 150 - entry.getValue());
                }
                else {
                    path.lineTo(i * 100, 150 - entry.getValue());
                }
                i++;
                if(i==8){
                    break;
                }
            }
            canvas.drawText("" + nominalPower, 15, y + 45, paint);
            canvas.drawText("" + nominalPower * 3 / 4, 15, y + 95, paint);
            canvas.drawText("" + nominalPower / 2, 15, y + 145, paint);
            canvas.drawText("" + nominalPower / 4, 15, y + 195, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(3);
            canvas.translate(x , y);
            canvas.drawPath(path, paint);
        }
    }

    public void setData(LinkedHashMap<Long, Float> dateMap){
        this.dataMap = dateMap;
    }

    public void setNominalPower(float nominalPower){
        this.nominalPower = nominalPower;
    }

}
