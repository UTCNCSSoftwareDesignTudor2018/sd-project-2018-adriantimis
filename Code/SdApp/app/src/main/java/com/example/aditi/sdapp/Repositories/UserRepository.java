package com.example.aditi.sdapp.Repositories;

import android.arch.lifecycle.LiveData;

import com.example.aditi.sdapp.Persistence.Entities.DAO.UserDAO;
import com.example.aditi.sdapp.Persistence.Entities.User;
import com.example.aditi.sdapp.RemoteDataSources.UserWebService;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by aditi on 20/05/2018.
 */

@Singleton
public class UserRepository {

    private UserWebService userWebService;
    private UserDAO userDAO;

    @Inject
    public UserRepository(UserDAO userDAO){

        this.userDAO = userDAO;

        this.userWebService = new Retrofit.Builder().baseUrl("http://192.168.0.220:8080/").build().create(UserWebService.class);

    }

    public LiveData<User> getUser(){

        return userDAO.getUser();

    }



}
