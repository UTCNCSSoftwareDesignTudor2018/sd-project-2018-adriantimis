package com.Project.SdProject.BusinessLogic.DataTransferObjects;

public class LineInformationDTO {

    private StudentDTO student;

    private LineDTO line1;
    private LineDTO line2;

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public LineDTO getLine1() {
        return line1;
    }

    public void setLine1(LineDTO line1) {
        this.line1 = line1;
    }

    public LineDTO getLine2() {
        return line2;
    }

    public void setLine2(LineDTO line2) {
        this.line2 = line2;
    }
}
