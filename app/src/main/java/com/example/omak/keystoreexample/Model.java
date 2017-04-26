package com.example.omak.keystoreexample;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Model extends RealmObject {

    public Model(){}

    @PrimaryKey
    private String id;
    private String message;
    private int age;

    public Model(String id,String message)

    {

        this.id = id;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
