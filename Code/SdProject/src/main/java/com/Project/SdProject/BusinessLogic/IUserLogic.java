package com.Project.SdProject.BusinessLogic;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.UserDTO;

public interface IUserLogic {

    UserDTO register(UserDTO userDTO);
    UserDTO login(UserDTO userDTO);
    UserDTO getUser(String id);

}
