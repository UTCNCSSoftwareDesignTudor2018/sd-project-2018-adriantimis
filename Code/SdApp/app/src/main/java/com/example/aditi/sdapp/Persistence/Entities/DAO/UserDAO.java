package com.example.aditi.sdapp.Persistence.Entities.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.aditi.sdapp.Persistence.Entities.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by aditi on 19/05/2018.
 */

@Dao
public interface UserDAO {

    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("DELETE FROM User")
    void delete();

    @Query("SELECT * from User LIMIT 1")
    LiveData<User> getUser();

}
