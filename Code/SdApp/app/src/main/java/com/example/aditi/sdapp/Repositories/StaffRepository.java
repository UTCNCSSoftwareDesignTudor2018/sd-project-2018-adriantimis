package com.example.aditi.sdapp.Repositories;

import android.arch.lifecycle.LiveData;

import com.example.aditi.sdapp.Persistence.Entities.DAO.StaffDAO;
import com.example.aditi.sdapp.Persistence.Entities.Line;
import com.example.aditi.sdapp.Persistence.Entities.Staff;
import com.example.aditi.sdapp.Persistence.Entities.Student;
import com.example.aditi.sdapp.RemoteDataSources.StaffWebService;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by aditi on 21/05/2018.
 */

@Singleton
public class StaffRepository {

    private StaffWebService staffWebService;

    private final StaffDAO staffDAO;
    private final Executor executor;

    @Inject
    public StaffRepository(StaffDAO staffDAO, Executor executor){

        this.staffDAO = staffDAO;
        this.executor = executor;

        this.staffWebService = new Retrofit.Builder().baseUrl("http://192.168.0.220:8080/").build().create(StaffWebService.class);

    }

    public Call<List<Line>> checkPassValidity(String username){

        return staffWebService.checkPassValidity(username);

    }

    public LiveData<Staff> getStaff(String username){

        getStaffAsync(username);
        return staffDAO.getStaff();

    }

    private void getStaffAsync(String username){

        executor.execute(() -> {

            staffDAO.delete();

            staffWebService.getStaff(username).enqueue(new Callback<Staff>() {
                @Override
                public void onResponse(Call<Staff> call, Response<Staff> response) {

                    staffDAO.save(response.body());

                }

                @Override
                public void onFailure(Call<Staff> call, Throwable t) {

                    // Error server

                }
            });

        });

    }

}
