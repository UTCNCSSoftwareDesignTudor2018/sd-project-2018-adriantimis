package com.example.aditi.sdapp.Persistence.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by aditi on 19/05/2018.
 */

@Entity
public class NfcCard {

    @PrimaryKey
    private int id;

    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
