package com.example.aditi.sdapp.Persistence.Entities.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.aditi.sdapp.Persistence.Entities.NfcCard;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by aditi on 19/05/2018.
 */

@Dao
public interface NfcCardDAO {

    @Insert(onConflict = REPLACE)
    void save(NfcCard nfcCard);

    @Delete
    void delete(NfcCard nfcCard);

    @Query("SELECT * FROM NfcCard")
    LiveData<List<NfcCard>> getNfcCards();

}
