package com.example.aditi.sdapp.Repositories;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.aditi.sdapp.Persistence.Entities.DAO.UserDAO;
import com.example.aditi.sdapp.Persistence.Entities.User;
import com.example.aditi.sdapp.RemoteDataSources.AuthWebService;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by aditi on 20/05/2018.
 */

//@Singleton
public class AuthRepository {

    private AuthWebService authWebService;

    private final UserDAO userDAO;
    private final Executor executor;

    @Inject
    public AuthRepository(UserDAO userDAO, Executor executor){
        this.userDAO = userDAO;
        this.executor = executor;

        this.authWebService = new Retrofit.Builder().baseUrl("http://192.168.0.220:8080/").build().create(AuthWebService.class);

    }

    public LiveData<User> login(User user){

        loginUserAsync(user);
        return userDAO.getUser();

    }

    public LiveData<User> registerUser(User user){

        registerUserAsync(user);
        return userDAO.getUser();

    }

    private void loginUserAsync(User user){

        executor.execute(() -> {

            userDAO.delete();

//            authWebService.loginUser(user).enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, retrofit2.Response<User> response) {
//
//                    userDAO.save(response.body());
//
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//
//                    // Error logging in
//
//                }
//            });

        });

    }

    private void registerUserAsync(User user){

        executor.execute(() -> {

            LiveData<User> roomUser = userDAO.getUser();
            if(roomUser.getValue() == null){

                //MutableLiveData<User> data = new MutableLiveData<>();

                authWebService.registerUser(user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, retrofit2.Response<User> response) {

                        //data.setValue(response.body());
                        userDAO.save(response.body());

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        // Do nothing

                    }
                });

            }else{

                // User may be already logged in

            }

        });

    }

}
