package com.example.aditi.sdapp.Persistence.Entities;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.aditi.sdapp.Persistence.Entities.Converters.DateConverter;
import com.example.aditi.sdapp.Persistence.Entities.DAO.BusPassDAO;
import com.example.aditi.sdapp.Persistence.Entities.DAO.LineDAO;
import com.example.aditi.sdapp.Persistence.Entities.DAO.NfcCardDAO;
import com.example.aditi.sdapp.Persistence.Entities.DAO.StaffDAO;
import com.example.aditi.sdapp.Persistence.Entities.DAO.StudentDAO;
import com.example.aditi.sdapp.Persistence.Entities.DAO.UserDAO;

/**
 * Created by aditi on 19/05/2018.
 */

@android.arch.persistence.room.Database(entities = {User.class, BusPass.class, Line.class, NfcCard.class, Staff.class, Student.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class Database extends RoomDatabase {

    public abstract BusPassDAO busPassDAO();
    public abstract LineDAO lineDAO();
    public abstract NfcCardDAO nfcCardDAO();
    public abstract StaffDAO staffDAO();
    public abstract StudentDAO studentDAO();
    public abstract UserDAO userDAO();

}
