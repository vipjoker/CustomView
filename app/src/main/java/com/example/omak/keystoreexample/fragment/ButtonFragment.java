package com.example.omak.keystoreexample.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omak.keystoreexample.MainActivity;
import com.example.omak.keystoreexample.R;
import com.example.omak.keystoreexample.databinding.FragmentItemBinding;
import com.example.omak.keystoreexample.di.DaggerAppComponent;
import com.example.omak.keystoreexample.di.SayHello;

import javax.inject.Inject;

/**
 * Created by omak on 4/27/17.
 */

public class ButtonFragment extends Fragment {
    FragmentItemBinding b;
    @Inject
    SayHello hello;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false);
        setListeners();
        ((MainActivity)getActivity()).appComp.inject(this);


        return b.getRoot();
    }

    private void setListeners() {
        b.btnAction.setOnClickListener(v->{
           hello.sayHello();
        });
    }
}
