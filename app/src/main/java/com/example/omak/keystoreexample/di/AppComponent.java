package com.example.omak.keystoreexample.di;


import com.example.omak.keystoreexample.fragment.ButtonFragment;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {
        AppModule.class,
        ContextModule.class
})
public interface AppComponent {
    void inject(ButtonFragment fragment);



}
