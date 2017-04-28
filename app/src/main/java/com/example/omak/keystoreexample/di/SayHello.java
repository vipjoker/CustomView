package com.example.omak.keystoreexample.di;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by omak on 4/27/17.
 */

public class SayHello {
    private Context mContext;

    public SayHello(Context context) {
        this.mContext = context;
    }
    public void sayHello(){
        Toast.makeText(mContext,"Hello",Toast.LENGTH_SHORT).show();
    }
}
