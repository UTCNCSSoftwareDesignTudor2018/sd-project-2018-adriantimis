package com.example.aditi.sdapp.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.database.sqlite.SQLiteBlobTooBigException;

import com.example.aditi.sdapp.DataTransferObjects.LineInformationDTO;
import com.example.aditi.sdapp.Persistence.Entities.Line;
import com.example.aditi.sdapp.Persistence.Entities.Student;
import com.example.aditi.sdapp.RemoteDataSources.StudentWebService;
import com.example.aditi.sdapp.Repositories.StudentRepository;

import java.util.LinkedList;
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

public class StudentViewModel extends ViewModel {

    private MutableLiveData<Student> student;
    private MutableLiveData<List<Line>> lines;

    private StudentRepository studentRepository;

    private StudentWebService studentWebService;

    public StudentViewModel(){

        studentWebService = new Retrofit.Builder().baseUrl("http://192.168.0.220:8080/").addConverterFactory(GsonConverterFactory.create()).build().create(StudentWebService.class);
        student = new MutableLiveData<>();
        lines = new MutableLiveData<>();

    }

//    @Inject
//    public StudentViewModel(StudentRepository studentRepository){
//        this.studentRepository = studentRepository;
//    }

    public void init(String username){

        //student = studentRepository.getStudent(username);
        //getStudentLines();

        studentWebService.getStudent(username).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {

                // Set the name of the student

                student.setValue(response.body());

//                student.getValue().setName(response.body().getName());
//                student.getValue().setSurname(response.body().getSurname());
//                student.getValue().setUniversity(response.body().getUniversity());

                // Make sure that the observer pattern from live data is working

                // After that create another call and get the lines

                studentWebService.getLines(response.body()).enqueue(new Callback<List<Line>>() {
                    @Override
                    public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {

                        // Set the lines in the ui, or make somehow the activity to observe the view model

                        if(response.body().size() == 2){

                            List<Line> lineList = new LinkedList<>();
                            lineList.add(response.body().get(0));
                            lineList.add(response.body().get(1));

                            lines.setValue(lineList);

//                            lines.getValue().add(response.body().get(0));
//                            lines.getValue().add(response.body().get(1));

                            // Again, make sure that the observer pattern from the live data objects is working

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Line>> call, Throwable t) {

                        // Show error message / popup
                        // Redirect to login page

                    }
                });

            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

                // Show error message / popup
                // Redirect to login page

            }
        });

    }

    public LiveData<List<Line>> getLines() {
        return lines;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public LiveData<Student> getStudent(){
        return student;
    }

    public void chooseLines(LineInformationDTO lineInformationDTO){

        studentWebService.chooseLines(lineInformationDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Success
                // Update the ui with a success message
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error
            }
        });

    }

    public void toogleAutomaticLineSelection(){

        studentWebService.toggleAutomaticLineSelection(null).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Success message
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error message
            }
        });

    }

    public void getStudentLines(){

        studentRepository.getLines(student.getValue()).enqueue(new Callback<List<Line>>() {
            @Override
            public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {

                // Success
                MutableLiveData<List<Line>> studentLines = new MutableLiveData<>();
                studentLines.setValue(response.body());

                lines = studentLines;

            }

            @Override
            public void onFailure(Call<List<Line>> call, Throwable t) {

                // Error

            }
        });

    }

}
