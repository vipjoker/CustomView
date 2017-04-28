package com.example.omak.keystoreexample;

import android.graphics.Canvas;
import android.graphics.Paint;


public class  Circle{
        Vector2 center;
        float radius;
        Paint paint;
        Circle (float x,float y, float radius,Paint paint){
            center = new Vector2(x,y);
            this.radius = radius;
            this.paint = paint;
        }

        boolean contains(float x ,float y){
            return radius > center.dst(x,y);
        }

        public void draw(Canvas canvas){
            canvas.drawCircle(center.x,center.y,radius,paint);
        }
    }


