package com.Project.SdProject.BusinessLogic;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.UserDTO;

public interface IUserLogic {

    boolean register(UserDTO userDTO);
    String login(UserDTO userDTO);

}
