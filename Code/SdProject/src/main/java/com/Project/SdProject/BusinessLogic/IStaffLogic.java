package com.Project.SdProject.BusinessLogic;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StaffDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StudentDTO;

import java.util.List;

public interface IStaffLogic {

    void createStaff(StaffDTO staffDTO);
    void generateNfcCard(StudentDTO studentDTO);
    List<LineDTO> checkPassValidity(String username);
    StaffDTO get(String username);

}
