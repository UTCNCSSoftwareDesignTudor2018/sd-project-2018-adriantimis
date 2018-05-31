package com.example.aditi.sdapp.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aditi.sdapp.Persistence.Entities.Student;
import com.example.aditi.sdapp.Persistence.Entities.User;
import com.example.aditi.sdapp.R;
import com.example.aditi.sdapp.Repositories.AuthRepository;
import com.example.aditi.sdapp.ViewModels.MainViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    AuthRepository authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        mainViewModel.getLoggedUser().observe(this, user -> {

            // The user has logged in
            // Redirect to student / staff page

            if(user.getUsername() != null && user.getPassword() != null) {

                if(user.getRole() == 1) {

                    Intent i = new Intent(this, StudentActivity.class);
                    i.putExtra("username", user.getUsername());

                    startActivity(i);

                }else if(user.getRole() == 2){

                    Intent i = new Intent(this, StaffActivity.class);
                    i.putExtra("username", user.getUsername());

                    startActivity(i);

                }

            }

        });


        mainViewModel.getRegisteredUser().observe(this, user -> {

            // The user has registered
            // Redirect to student / staff page

            if(user.getUsername() != null && user.getPassword() != null) {

                Intent i = new Intent(this, StudentActivity.class);

                i.putExtra("username", mainViewModel.getLoggedUser().getValue().getUsername());

                //startActivity(i);

            }

        });


        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {

            User user = new User();

            user.setUsername(((EditText) findViewById(R.id.usernameLogin)).getText().toString());
            user.setPassword(((EditText) findViewById(R.id.passwordLogin)).getText().toString());

            mainViewModel.login(user);

            User dbUser = mainViewModel.getLoggedUser().getValue();

            if(dbUser != null){

                Intent i = new Intent(this, StudentActivity.class);
                i.putExtra("username", dbUser.getUsername());

                //startActivity(i);

            }

        });

        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(view -> {

            User user = new User();
            Student student = new Student();

            user.setUsername(((EditText)findViewById(R.id.usernameRegister)).getText().toString());
            user.setPassword(((EditText)findViewById(R.id.passwordRegister)).getText().toString());

            student.setName(((EditText)findViewById(R.id.nameRegister)).getText().toString());
            student.setSurname(((EditText)findViewById(R.id.surnameRegister)).getText().toString());
            student.setUniversity(((EditText)findViewById(R.id.universityRegister)).getText().toString());
            student.setUsername(((EditText)findViewById(R.id.usernameRegister)).getText().toString());

            mainViewModel.register(user, student);

        });

    }
}
