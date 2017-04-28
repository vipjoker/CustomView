package com.example.omak.keystoreexample;

import android.app.Application;
import android.util.Log;

import com.example.omak.keystoreexample.di.DaggerAppComponent;

import java.io.File;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;


public class CustomApplication extends Application {
    boolean shouldMigrate = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        byte[] key = new byte[64];
        for (int i = 0; i < 64; i++) {
            key[i] = 7;
        }





            RealmConfiguration configuration = new RealmConfiguration
                    .Builder()
                   // .schemaVersion(1)
                   // .migration(new Migration())
                    .build();

        if(shouldMigrate){

            migrateToEncryptedDb(key,configuration);

        }else{

            Realm.setDefaultConfiguration(configuration);
        }




    }
    class Migration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();
            if(oldVersion == 1){
                schema.get("Model").addField("age",int.class);
            }

        }
    }

    private void migrateToEncryptedDb(byte[] key,RealmConfiguration oldConfiguration){
        Realm.getInstance(oldConfiguration).writeEncryptedCopyTo(new File(oldConfiguration.getRealmDirectory(),"encrypted"),key);

        RealmConfiguration encryptedCondfiguration = new RealmConfiguration.Builder()
                .schemaVersion(2)
                .encryptionKey(key)
                .name("encrypted")
                .migration(new Migration()).build();
        Realm.setDefaultConfiguration(encryptedCondfiguration);
    }
}
