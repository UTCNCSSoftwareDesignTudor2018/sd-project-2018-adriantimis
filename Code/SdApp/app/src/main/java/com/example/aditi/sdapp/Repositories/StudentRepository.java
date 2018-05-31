package com.example.aditi.sdapp.Repositories;

import android.arch.lifecycle.LiveData;

import com.example.aditi.sdapp.DataTransferObjects.LineInformationDTO;
import com.example.aditi.sdapp.Persistence.Entities.DAO.StudentDAO;
import com.example.aditi.sdapp.Persistence.Entities.Line;
import com.example.aditi.sdapp.Persistence.Entities.Student;
import com.example.aditi.sdapp.RemoteDataSources.StudentWebService;

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
public class StudentRepository {

    private StudentWebService studentWebService;

    private final StudentDAO studentDAO;
    private final Executor executor;

    @Inject
    public StudentRepository(StudentDAO studentDAO, Executor executor){

        this.studentDAO = studentDAO;
        this.executor = executor;

        this.studentWebService = new Retrofit.Builder().baseUrl("http://192.168.0.220:8080/").build().create(StudentWebService.class);

    }

    public void chooseLine(LineInformationDTO lineInformationDTO){

        studentWebService.chooseLines(lineInformationDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                 // Success
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                // Success


            }
        });

    }

    public void toggleAutomaticLineSelection(Student student){

        studentWebService.toggleAutomaticLineSelection(student).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // success
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // success
            }
        });

    }

    public Call<List<Line>> getLines(Student student){

        return studentWebService.getLines(student);

    }

    public LiveData<Student> getStudent(String username){

        getUserAsync(username);
        return studentDAO.getStudent();

    }

    private void getUserAsync(String username){

        executor.execute(() -> {

            studentDAO.delete();

            studentWebService.getStudent(username).enqueue(new Callback<Student>() {
                @Override
                public void onResponse(Call<Student> call, Response<Student> response) {

                    studentDAO.save(response.body());

                }

                @Override
                public void onFailure(Call<Student> call, Throwable t) {

                    // Server issues

                }
            });

        });

    }

}
