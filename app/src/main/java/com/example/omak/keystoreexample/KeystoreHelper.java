package com.example.omak.keystoreexample;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by omak on 4/25/17.
 */

public class KeystoreHelper {
    public static String TAG = "MainActivity";


    private static final String KEY_STORE_ALIAS = "mediteo_database_key";
    private static final char[] KEY_PROTECTION_PASSWORD = "mediteo_protection_parameter".toCharArray();


    public static String KEYSTORE_NAME = "Mykeystore";
    public static byte[] getKey(Context context) {
        byte[] key = null;
        FileInputStream fis= null;
        try {
            fis = context.openFileInput(KEYSTORE_NAME);

            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(fis, KEY_PROTECTION_PASSWORD);
            Log.v("main",ks.aliases().toString());
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(KEY_PROTECTION_PASSWORD);
            KeyStore.SecretKeyEntry entry = (KeyStore.SecretKeyEntry) ks.getEntry(KEY_STORE_ALIAS, protParam);
            if(entry == null) return null;
            key = entry.getSecretKey().getEncoded();

        } catch (Exception e) {
            Log.v("main","keystore crashed");
            e.printStackTrace();
        }finally {
            if(fis!=null) try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return key;

    }

    public static void saveKey(byte[] key,Context context) {
        FileOutputStream out = null;
        try
        {
            out = context.openFileOutput(KEYSTORE_NAME,Context.MODE_PRIVATE);
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, KEY_PROTECTION_PASSWORD);
            SecretKey sk = new SecretKeySpec(key, 0, key.length, "AES");
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(KEY_PROTECTION_PASSWORD);
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(sk);
            ks.setEntry(KEY_STORE_ALIAS, skEntry, protParam);
            ks.store(out, KEY_PROTECTION_PASSWORD);
        } catch (Exception e) {

            Log.v("main","keystore crashed");
            e.printStackTrace();
        }finally {
            if(out !=null) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
