package com.example.aditi.sdapp.RemoteDataSources;

import com.example.aditi.sdapp.Persistence.Entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aditi on 20/05/2018.
 */

public interface UserWebService {

    @GET("user/get?id={id}")
    Call<User> getUser(@Path("id") String id);

}
