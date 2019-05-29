package com.example.capstone3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.Boolean.FALSE;

public class User {

    private String Phone;
    private String Name;
    private String PName;
    private String Password;
    private String Classification;
    private int Connect;
    private int Lock;
    private int Geofence;

    public User() { }

    public User(String Phone,String Name,String PName,String Password,String Classification) {
        this.Phone = Phone;
        this.Name = Name;
        this.PName = PName;
        this.Password = Password;
        this.Classification = Classification;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getClassification() {
        return Classification;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }


    public int getConnect() {
        return Connect;
    }

    public void setConnect(int connect) {
        Connect = connect;
    }

    public int getLock() {
        return Lock;
    }

    public void setLock(int lock) {
        Lock = lock;
    }

    public int getGeofence() {
        return Geofence;
    }

    public void setGeofence(int geofence) {
        Geofence = geofence;
    }
}
