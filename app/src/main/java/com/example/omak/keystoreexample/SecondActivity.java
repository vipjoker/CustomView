package com.example.omak.keystoreexample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.omak.keystoreexample.di.DaggerAppComponent;
import com.example.omak.keystoreexample.di.SayHello;

import javax.inject.Inject;

/**
 * Created by omak on 4/25/17.
 */

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        https://www.behance.net/gallery/34331871/Garnet-(Music-App-Ui)
//        https://github.com/WritingMinds/ffmpeg-android/releases/tag/v0.3.4
        if (getActionBar() != null) {
            getActionBar().setHomeButtonEnabled(true);

        }
        FrameLayout frameLayout = new FrameLayout(this);
        setContentView(frameLayout);






        frameLayout.addView(new DrawView(this));
        frameLayout.setBackgroundColor(Color.BLACK);

        setContentView(frameLayout);
    }
}
