package com.Project.SdProject.BusinessLogic;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineInformationDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StudentDTO;

import java.util.List;

public interface IStudentLogic {

    void createStudent(StudentDTO studentDTO);
    void chooseLines(LineInformationDTO lineInformationDTO);
    void toggleAutomaticLineSelection(StudentDTO studentDTO);
    StudentDTO get(String username);
    List<LineDTO> getLines(StudentDTO studentDTO);


}
