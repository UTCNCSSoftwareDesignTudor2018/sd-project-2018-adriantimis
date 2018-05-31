package com.example.aditi.sdapp.RemoteDataSources;

import com.example.aditi.sdapp.Persistence.Entities.Line;
import com.example.aditi.sdapp.Persistence.Entities.Staff;
import com.example.aditi.sdapp.Persistence.Entities.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by aditi on 21/05/2018.
 */

public interface StaffWebService {

    @POST("staff/create")
    Call<Void> createStaff(@Body Staff staff);

    @POST("staff/generateNfcCard")
    Call<Void> generateNfcCard(@Body Student student);

    @GET("staff/checkPassValidity")
    Call<List<Line>> checkPassValidity(@Query("username") String username);

    @GET("staff/get?username={username}")
    Call<Staff> getStaff(@Path("username") String username);

}
