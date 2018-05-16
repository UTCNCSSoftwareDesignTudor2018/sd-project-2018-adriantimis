package com.Project.SdProject.BusinessLogic.Implementatios;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.UserDTO;
import com.Project.SdProject.BusinessLogic.IUserLogic;
import com.Project.SdProject.DataAccess.Entities.User;
import com.Project.SdProject.DataAccess.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogic implements IUserLogic {

    private UserRepository userRepository;

    @Autowired
    public UserLogic(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(UserDTO userDTO) {

        if(userRepository.findById(userDTO.getUsername()).orElse(null) != null){
            return false;
        }

        User newUser = new User();
        newUser.setPassword(userDTO.getPassword());
        newUser.setUsername(userDTO.getUsername());

        userRepository.save(newUser);

        return true;

    }

    @Override
    public String login(UserDTO userDTO) {

        User dbUser = userRepository.findById(userDTO.getUsername()).orElse(null);

        if(dbUser == null){
            return null;
        }

        if(userDTO.getPassword().equals(dbUser.getPassword())){
            return userDTO.getUsername();
        }

        return null;

    }

}
