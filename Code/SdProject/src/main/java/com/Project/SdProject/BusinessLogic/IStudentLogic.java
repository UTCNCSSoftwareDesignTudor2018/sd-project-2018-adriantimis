package com.Project.SdProject.BusinessLogic;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineInformationDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StudentDTO;

public interface IStudentLogic {

    void createStudent(StudentDTO studentDTO);
    void chooseLines(LineInformationDTO lineInformationDTO);
    void toggleAutomaticLineSelection(StudentDTO studentDTO);


}
