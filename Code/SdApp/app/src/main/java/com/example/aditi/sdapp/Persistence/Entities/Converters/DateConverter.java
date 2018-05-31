package com.example.aditi.sdapp.Persistence.Entities.Converters;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

/**
 * Created by aditi on 23/05/2018.
 */


public class DateConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value){

        return value == null ? null : new Date(value);

    }

    @TypeConverter
    public static long dateToTimestamp(Date date){

        return date == null ? null : date.getTime();

    }

}
