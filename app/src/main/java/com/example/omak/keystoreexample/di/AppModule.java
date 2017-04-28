package com.example.omak.keystoreexample.di;


import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {





    @Provides
    public SayHello createHello(Context context){
        return new SayHello(context);
    }


}
