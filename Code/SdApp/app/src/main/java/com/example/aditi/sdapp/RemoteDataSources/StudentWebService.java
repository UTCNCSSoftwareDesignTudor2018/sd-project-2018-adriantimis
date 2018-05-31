package com.example.aditi.sdapp.RemoteDataSources;

import com.example.aditi.sdapp.DataTransferObjects.LineInformationDTO;
import com.example.aditi.sdapp.Persistence.Entities.Line;
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


public interface StudentWebService {

    @POST("student/create")
    Call<Void> createStudent(@Body Student newStudent);

    @POST("student/chooseLines")
    Call<Void> chooseLines(@Body LineInformationDTO lineInformationDTO);

    @POST("student/toggleAutomaticLineSelection")
    Call<Void> toggleAutomaticLineSelection(@Body Student student);

    @GET("student/get")
    Call<Student> getStudent(@Query("username") String username);

    @POST("student/getLines")
    Call<List<Line>> getLines(@Body Student student);

}
