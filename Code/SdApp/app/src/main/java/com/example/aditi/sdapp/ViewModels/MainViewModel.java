package com.example.aditi.sdapp.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.aditi.sdapp.DataTransferObjects.UserDTO;
import com.example.aditi.sdapp.Persistence.Entities.Student;
import com.example.aditi.sdapp.Persistence.Entities.User;
import com.example.aditi.sdapp.RemoteDataSources.AuthWebService;
import com.example.aditi.sdapp.RemoteDataSources.StudentWebService;
import com.example.aditi.sdapp.Repositories.AuthRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aditi on 22/05/2018.
 */

public class MainViewModel extends ViewModel {

    private AuthWebService authWebService;
    private StudentWebService studentWebService;

    private MutableLiveData<User> loggedUser;
    private MutableLiveData<User> registeredUser;

    public MainViewModel(){

        MutableLiveData<User> newUser = new MutableLiveData<>();
        newUser.setValue(new User());
        loggedUser = newUser;
        registeredUser = newUser;
        authWebService = new Retrofit.Builder().baseUrl("http://192.168.0.220:8080/").addConverterFactory(GsonConverterFactory.create()).build().create(AuthWebService.class);
        studentWebService = new Retrofit.Builder().baseUrl("http://192.168.0.220:8080/").addConverterFactory(GsonConverterFactory.create()).build().create(StudentWebService.class);

    }

//    @Inject
//    public MainViewModel(AuthRepository authRepository){
//        this.authRepository = authRepository;
//    }

    public void login(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());

        authWebService.loginUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body() != null) {

//                    MutableLiveData<User> newUser = new MutableLiveData<>();
//                    newUser.setValue(response.body());
//
//                    loggedUser = newUser;

                    //loggedUser.getValue().setUsername(response.body().getUsername());
                    //loggedUser.getValue().setPassword(response.body().getPassword());

                    User newUser = new User();
                    newUser.setUsername(response.body().getUsername());
                    newUser.setPassword(response.body().getPassword());
                    newUser.setRole(response.body().getRole());
                    loggedUser.setValue(newUser);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        //loggedUser = authRepository.login(user);

    }

    public void register(User user, Student student){

        authWebService.registerUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body() != null) {

                    studentWebService.createStudent(student).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            // Then go to the user page

                            registeredUser.getValue().setUsername(user.getUsername());
                            registeredUser.getValue().setPassword(user.getPassword());

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {



            }
        });

        //registeredUser = authRepository.registerUser(user);

    }

    public LiveData<User> getLoggedUser() {
        return loggedUser;
    }

    public LiveData<User> getRegisteredUser() {
        return registeredUser;
    }
}
