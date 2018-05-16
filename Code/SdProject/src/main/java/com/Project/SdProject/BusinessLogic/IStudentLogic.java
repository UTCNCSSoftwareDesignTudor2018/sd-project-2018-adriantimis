package com.Project.SdProject.BusinessLogic;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineDTO;

public interface IStudentLogic {

    void chooseLines(LineDTO line1, LineDTO line2);
    void toggleAutomaticLineSelection();


}
