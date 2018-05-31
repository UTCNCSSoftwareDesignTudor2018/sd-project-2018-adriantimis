package com.example.aditi.sdapp.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.aditi.sdapp.Persistence.Entities.Line;
import com.example.aditi.sdapp.Persistence.Entities.Staff;
import com.example.aditi.sdapp.Persistence.Entities.Student;
import com.example.aditi.sdapp.RemoteDataSources.StaffWebService;
import com.example.aditi.sdapp.Repositories.StaffRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aditi on 21/05/2018.
 */

public class StaffViewModel extends ViewModel {

    private MutableLiveData<Staff> staff;
    private MutableLiveData<List<Line>> checkedLines;

    private StaffWebService staffWebService;

    public StaffViewModel(){

        this.staffWebService = new Retrofit.Builder().baseUrl("http://192.168.0.220:8080/").addConverterFactory(GsonConverterFactory.create()).build().create(StaffWebService.class);
        staff = new MutableLiveData<>();
        checkedLines = new MutableLiveData<>();

    }

//    @Inject
//    public StaffViewModel(StaffRepository staffRepository){
//        this.staffRepository = staffRepository;
//    }

    public void init(String username){

        if(staff != null){
            return;
        }

        //staff = staffRepository.getStaff(username);

    }

    public LiveData<List<Line>> getCheckedLines() {
        return checkedLines;
    }

    public LiveData<Staff> getStaff(){
        return staff;
    }

    public void checkPassValidity(String username){

        staffWebService.checkPassValidity(username).enqueue(new Callback<List<Line>>() {
            @Override
            public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {

                checkedLines.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Line>> call, Throwable t) {

            }
        });

    }

}
