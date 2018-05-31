package com.Project.SdProject.BusinessLogic.Implementatios;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StaffDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StudentDTO;
import com.Project.SdProject.BusinessLogic.IStaffLogic;
import com.Project.SdProject.DataAccess.Entities.NfcCard;
import com.Project.SdProject.DataAccess.Entities.Staff;
import com.Project.SdProject.DataAccess.Entities.Student;
import com.Project.SdProject.DataAccess.Entities.User;
import com.Project.SdProject.DataAccess.Repositories.StaffRepository;
import com.Project.SdProject.DataAccess.Repositories.StudentRepository;
import com.Project.SdProject.DataAccess.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class StaffLogic implements IStaffLogic {

    private UserRepository userRepository;
    private StaffRepository staffRepository;
    private StudentRepository studentRepository;
    private DtoFactory dtoFactory;

    @Autowired
    public StaffLogic(UserRepository userRepository, StaffRepository staffRepository, StudentRepository studentRepository, DtoFactory dtoFactory){
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.studentRepository = studentRepository;
        this.dtoFactory = dtoFactory;
    }

    @Override
    public void createStaff(StaffDTO staffDTO) {

        Staff staff = new Staff();
        User user = userRepository.findById(staffDTO.getUsername()).orElse(null);

        if(user != null) {

            staff.setName(staffDTO.getName());
            staff.setSurname(staffDTO.getSurname());

            staff.setUser(user);

            staffRepository.save(staff);

        }


    }

    @Override
    public void generateNfcCard(StudentDTO studentDTO) {

        Student student = studentRepository.findById(studentDTO.getId()).orElse(null);

        if(student !=  null) {

            NfcCard newCard = new NfcCard();
            newCard.setPassword(String.valueOf(student.getId()));

            student.setNfcCard(newCard);

            studentRepository.save(student);

        }


    }

    @Override
    public List<LineDTO> checkPassValidity(String username){

        Student student = studentRepository.findByUser_Username(username);

        if(student == null){
            return null;
        }

        List<LineDTO> lineDTOS = new LinkedList<>();
        Calendar calendar = Calendar.getInstance();

        student.getBusPasses().forEach(b -> {

            if(b.getExpirationDate().compareTo(new Date(calendar.getTimeInMillis())) > 0){
                b.getLines().forEach(l -> {

                    lineDTOS.add(dtoFactory.createDTO(l));

                });
            }

        });

        return lineDTOS;

    }

    @Override
    public StaffDTO get(String username) {
        return dtoFactory.createDTO(staffRepository.findByUser_Username(username));
    }

}
