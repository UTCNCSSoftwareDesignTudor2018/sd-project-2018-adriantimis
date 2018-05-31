package com.example.aditi.sdapp.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aditi.sdapp.DataTransferObjects.LineInformationDTO;
import com.example.aditi.sdapp.Persistence.Entities.Line;
import com.example.aditi.sdapp.Persistence.Entities.Student;
import com.example.aditi.sdapp.R;
import com.example.aditi.sdapp.ViewModels.StudentViewModel;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        StudentViewModel studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        String username = getIntent().getExtras().get("username").toString();

        studentViewModel.init(username);

        // Initialize the rest of the activity by getting the lines, see if the automatic toggle selection is activated


        studentViewModel.getStudent().observe(this, student -> {

            // Update the ui according to this information

            if(student.getName() != null) {

                ((TextView) findViewById(R.id.studentName)).setText(student.getName());

            }

        });

        studentViewModel.getLines().observe(this, lines -> {

            // Update the ui with the new lines

            ((TextView)findViewById(R.id.primulAutobuz)).setText("Line 1 - " + lines.get(0).getLineNumber());
            ((TextView)findViewById(R.id.alDoileaAutobuz)).setText("Line 2 - " + lines.get(1).getLineNumber());

        });


        Button addLines = findViewById(R.id.selectLines);

        addLines.setOnClickListener(view -> {

            int line1Number = Integer.parseInt(((EditText) findViewById(R.id.line1Select)).getText().toString());
            int line2Number = Integer.parseInt(((EditText) findViewById(R.id.line2Select)).getText().toString());

            LineInformationDTO lineInformationDTO = new LineInformationDTO();

            Line line1 = new Line();
            line1.setLineNumber(line1Number);
            Line line2 = new Line();
            line2.setLineNumber(line2Number);

            lineInformationDTO.setLine1(line1);
            lineInformationDTO.setLine2(line2);

            Student student = new Student();
            student.setUsername(username);

            lineInformationDTO.setStudent(student);

            studentViewModel.chooseLines(lineInformationDTO);

        });

    }
}
