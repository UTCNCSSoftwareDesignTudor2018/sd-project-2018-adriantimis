package com.example.aditi.sdapp.DataTransferObjects;

import com.example.aditi.sdapp.Persistence.Entities.Line;
import com.example.aditi.sdapp.Persistence.Entities.Student;

/**
 * Created by aditi on 21/05/2018.
 */

public class LineInformationDTO {

    private Student student;

    private Line line1;
    private Line line2;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Line getLine1() {
        return line1;
    }

    public void setLine1(Line line1) {
        this.line1 = line1;
    }

    public Line getLine2() {
        return line2;
    }

    public void setLine2(Line line2) {
        this.line2 = line2;
    }
}
