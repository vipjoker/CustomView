package com.example.omak.keystoreexample.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by omak on 4/27/17.
 */
@Module
public class ContextModule {

    private Context mContext;

    public ContextModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context getContext(){
        return mContext;
    }
}
