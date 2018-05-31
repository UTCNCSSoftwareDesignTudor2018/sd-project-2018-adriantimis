package com.example.aditi.sdapp.Persistence.Entities.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.aditi.sdapp.Persistence.Entities.Student;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by aditi on 20/05/2018.
 */

@Dao
public interface StudentDAO {

    @Insert(onConflict = REPLACE)
    void save(Student student);

    @Query("DELETE FROM Student")
    void delete();

    @Query("SELECT * FROM Student LIMIT 1")
    LiveData<Student> getStudent();

}
