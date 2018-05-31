package com.Project.SdProject.BusinessLogic.Implementatios;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.UserDTO;
import com.Project.SdProject.BusinessLogic.IUserLogic;
import com.Project.SdProject.DataAccess.Entities.Staff;
import com.Project.SdProject.DataAccess.Entities.Student;
import com.Project.SdProject.DataAccess.Entities.User;
import com.Project.SdProject.DataAccess.Repositories.StaffRepository;
import com.Project.SdProject.DataAccess.Repositories.StudentRepository;
import com.Project.SdProject.DataAccess.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogic implements IUserLogic {

    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private StaffRepository staffRepository;
    private DtoFactory dtoFactory;

    @Autowired
    public UserLogic(UserRepository userRepository, StudentRepository studentRepository, StaffRepository staffRepository, DtoFactory dtoFactory){
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
        this.dtoFactory = dtoFactory;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {

        if(userRepository.findById(userDTO.getUsername()).orElse(null) != null){
            return null;
        }

        User newUser = new User();
        newUser.setPassword(userDTO.getPassword());
        newUser.setUsername(userDTO.getUsername());

        return dtoFactory.createDTO(userRepository.save(newUser));

    }

    @Override
    public UserDTO login(UserDTO userDTO) {

        User dbUser = userRepository.findById(userDTO.getUsername()).orElse(null);

        if(dbUser == null){
            return null;
        }

        if(userDTO.getPassword().equals(dbUser.getPassword())){

            Student student = studentRepository.findByUser_Username(dbUser.getUsername());
            Staff staff = staffRepository.findByUser_Username(dbUser.getUsername());

            if(staff != null){
                userDTO.setRole(2);
            }else if(student != null){
                userDTO.setRole(1);
            }else{
                userDTO.setRole(0);
            }

            return userDTO;
        }

        return null;

    }

    @Override
    public UserDTO getUser(String id) {

        return dtoFactory.createDTO(userRepository.findById(id).orElse(null));

    }

}
