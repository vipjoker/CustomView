package com.example.omak.keystoreexample;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.omak.keystoreexample.di.AppComponent;
import com.example.omak.keystoreexample.di.AppModule;
import com.example.omak.keystoreexample.di.ContextModule;
import com.example.omak.keystoreexample.di.DaggerAppComponent;
import com.example.omak.keystoreexample.fragment.ButtonFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    Button btnSave,btnLoad;
    TextView tvResult;
    EditText etPassword;
    public AppComponent appComp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setFragments();
        appComp = DaggerAppComponent.builder().appModule(new AppModule()).contextModule(new ContextModule(this)).build();


    }

    private void setFragments() {
        getSupportFragmentManager().beginTransaction().add(R.id.flLeft,new ButtonFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.flCenter,new ButtonFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.flRight,new ButtonFragment()).commit();
    }


    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }




}
