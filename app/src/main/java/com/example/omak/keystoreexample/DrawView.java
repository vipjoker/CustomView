package com.example.omak.keystoreexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class DrawView extends SurfaceView implements SurfaceHolder.Callback {

        private DrawThread drawThread;

    public Paint p ;
        Bitmap bitmap ;
    BitmapShader shader;
    Paint shaderPaint;
    Circle handler1,handler2;
    List<Circle> circles;
        public DrawView(Context context) {
            super(context);
            p = new Paint();

            circles = new ArrayList<>();
            handler1 = new Circle(10,200,20);
            handler2 = new Circle(30,30,20);
            circles.add(handler1);
            circles.add(handler2);
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.girl);

            shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            Matrix m = new Matrix();
            m.setScale(.2f,.2f);
            shader.setLocalMatrix(m);
            shaderPaint = new Paint();
            shaderPaint.setShader(shader);
            this.setBackgroundColor(Color.TRANSPARENT);
            this.setZOrderOnTop(true); //necessary
            getHolder().setFormat(PixelFormat.TRANSPARENT);
            getHolder().addCallback(this);
            setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                       float x = event.getX();
                       float y = event.getY();
                    for(Circle c : circles){
                        if(c.contains(new Vector2(x,y))){

                            c.center.set(x,y);
                            return true;
                        }else{

                        }
                    }
                        Log.v("works","touch");


                    return true;
                }
            });
        }




    @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            drawThread = new DrawThread(getHolder());

            drawThread.setRunning(true);
            drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            drawThread.setRunning(false);
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

        class DrawThread extends Thread {

            private boolean running = false;
            private SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {
                this.surfaceHolder = surfaceHolder;
            }

            public void setRunning(boolean running) {
                this.running = running;
            }

            @Override
            public void run() {
                Canvas canvas;
                while (running) {
                    canvas = null;
                    try {
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas == null)
                            continue;
                        draw(canvas);

                    } finally {
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
            double radius =0;
            float v = 0;
            void draw(Canvas canvas){
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                Path path = new Path();





                path.moveTo(0,getHeight()/2);
                path.rLineTo(getWidth()/2 -20,0);

                path.cubicTo(handler1.center.x,handler1.center.y,handler2.center.x,handler2.center.y,20,20);

                p.setStyle(Paint.Style.FILL_AND_STROKE);
                p.setFlags(Paint.ANTI_ALIAS_FLAG);
                p.setColor(Color.RED);
                p.setStrokeWidth(2);

                canvas.drawPath(path,p);
                for(Circle c:circles){
                    c.draw(canvas);
                }

            }







        }
         class Circle{
            Vector2 center;
            float radius;
            Circle (float x,float y, float radius){
                center = new Vector2(x,y);
                this.radius = radius;
            }

            boolean contains(Vector2 p){
                return radius < center.dst(p);
            }

            public void draw(Canvas canvas){
                canvas.drawCircle(center.x,center.y,radius,shaderPaint);
            }
        }






    }

