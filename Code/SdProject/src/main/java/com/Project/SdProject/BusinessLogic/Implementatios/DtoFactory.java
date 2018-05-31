package com.Project.SdProject.BusinessLogic.Implementatios;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.*;
import com.Project.SdProject.DataAccess.Entities.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class DtoFactory {

    public BusPassDTO createDTO(BusPass busPass){

        BusPassDTO busPassDTO = new BusPassDTO();

        busPassDTO.setId(busPass.getId());
        busPassDTO.setExpirationDate(busPass.getExpirationDate());

        return busPassDTO;

    }

    public LineDTO createDTO(Line line){

        LineDTO lineDTO = new LineDTO();

        lineDTO.setLineNumber(line.getLineNumber());

        return lineDTO;

    }

    public StaffDTO createDTO(Staff staff){

        StaffDTO staffDTO = new StaffDTO();

        staffDTO.setId(staff.getId());
        staffDTO.setName(staff.getName());
        staffDTO.setSurname(staff.getSurname());
        staffDTO.setUsername(staff.getUser().getUsername());

        staffDTO.setRoles(new LinkedList<>());
        staff.getRoles().forEach(r -> staffDTO.getRoles().add(r.getRoleName()));

        return staffDTO;

    }

    public StudentDTO createDTO(Student student){

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setUniversity(student.getUniversity());
        studentDTO.setUsername(student.getUser().getUsername());

        studentDTO.setBusPasses(new LinkedList<>());
        student.getBusPasses().forEach(b -> studentDTO.getBusPasses().add(createDTO(b)));

        return studentDTO;

    }

    public UserDTO createDTO(User user){

        UserDTO userDTO = new UserDTO();

        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());

        return userDTO;

    }

    public LineInformationDTO createDTO(Student student, Line line1, Line line2){

        LineInformationDTO lineInformationDTO = new LineInformationDTO();

        lineInformationDTO.setStudent(createDTO(student));
        lineInformationDTO.setLine1(createDTO(line1));
        lineInformationDTO.setLine2(createDTO(line2));

        return lineInformationDTO;

    }

}
