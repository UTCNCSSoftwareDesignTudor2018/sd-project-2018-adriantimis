package com.example.aditi.sdapp.Persistence.Entities.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.aditi.sdapp.Persistence.Entities.Staff;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by aditi on 19/05/2018.
 */

@Dao
public interface StaffDAO {

    @Insert(onConflict = REPLACE)
    void save(Staff staff);

    @Query("DELETE FROM Staff")
    void delete();

    @Query("SELECT * FROM Staff LIMIT 1")
    LiveData<Staff> getStaff();

}
