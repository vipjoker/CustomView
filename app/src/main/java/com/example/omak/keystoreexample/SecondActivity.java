package com.example.omak.keystoreexample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

/**
 * Created by omak on 4/25/17.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        https://www.behance.net/gallery/34331871/Garnet-(Music-App-Ui)
//        https://github.com/WritingMinds/ffmpeg-android/releases/tag/v0.3.4
        FrameLayout frameLayout = new FrameLayout(this);
        setContentView(frameLayout);


        ColorDrawable drawable = new ColorDrawable(Color.parseColor("#ff00ff"));
        frameLayout.setBackground(drawable);
        setContentView(frameLayout);
    }
}
