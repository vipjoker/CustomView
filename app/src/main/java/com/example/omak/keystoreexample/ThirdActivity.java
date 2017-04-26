package com.example.omak.keystoreexample;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.omak.keystoreexample.databinding.ThirdActiivityBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class ThirdActivity extends Activity {
    ThirdActiivityBinding b;
    CustomAdapter adapter;
    Realm realm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        realm = Realm.getDefaultInstance();
        b = DataBindingUtil.setContentView(this,R.layout.third_actiivity);
        setListView();


        b.btnAdd.setOnClickListener(this::onAdd);
        listenDatabase();
    }

    private void onAdd(View view) {

        String text = b.etText.getText().toString();
        if(text.trim().length() ==0){
            Toast.makeText(this,"Enter text",Toast.LENGTH_SHORT).show();
            return;
        }
        String id = UUID.randomUUID().toString();
        Model model = new Model(id,text);
        realm.executeTransaction( realm ->{
            realm.copyToRealm(model);
        });



    }

    private void listenDatabase(){

        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm element) {
               updateList();
            }
        });

    }


    void updateList(){
        RealmResults<Model> all = realm.where(Model.class).findAll();
        adapter.setList(all);
        adapter.notifyDataSetChanged();
        if(all.size() > 2)
        b.rvList.smoothScrollToPosition(all.size()-1);
    }


    private void setListView() {


         adapter = new CustomAdapter(this, Collections.emptyList());
        LinearLayoutManager manager = new LinearLayoutManager(this);

        b.rvList.setLayoutManager(manager);
        b.rvList.setAdapter(adapter);
        updateList();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
