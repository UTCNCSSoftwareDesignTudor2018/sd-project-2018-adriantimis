package com.example.aditi.sdapp.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.aditi.sdapp.Persistence.Entities.User;
import com.example.aditi.sdapp.Repositories.UserRepository;

import java.net.UnknownServiceException;

import javax.inject.Inject;

/**
 * Created by aditi on 19/05/2018.
 */

public class UserProfileViewModel extends ViewModel {

    private LiveData<User> user;

    private UserRepository userRepository;

    public UserProfileViewModel(){

    }

//    @Inject
//    public UserProfileViewModel(UserRepository userRepository){
//
//        this.userRepository = userRepository;
//
//    }

    public void init(){

        if(user != null){
            return;
        }

        this.user = userRepository.getUser();

    }

    public LiveData<User> getUser(){

        return this.user;

    }

}
