package com.example.aditi.sdapp.RemoteDataSources;

import com.example.aditi.sdapp.DataTransferObjects.UserDTO;
import com.example.aditi.sdapp.Persistence.Entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by aditi on 20/05/2018.
 */

public interface AuthWebService {

    @POST("auth/register")
    Call<User> registerUser(@Body User user);

    @POST("auth/login")
    Call<User> loginUser(@Body User user);

}
