package com.Project.SdProject.BusinessLogic.Implementatios;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineInformationDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StudentDTO;
import com.Project.SdProject.BusinessLogic.IStudentLogic;
import com.Project.SdProject.DataAccess.Entities.BusPass;
import com.Project.SdProject.DataAccess.Entities.Line;
import com.Project.SdProject.DataAccess.Entities.Student;
import com.Project.SdProject.DataAccess.Entities.User;
import com.Project.SdProject.DataAccess.Repositories.LineRepository;
import com.Project.SdProject.DataAccess.Repositories.StudentRepository;
import com.Project.SdProject.DataAccess.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentLogic implements IStudentLogic {

    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private LineRepository lineRepository;

    @Autowired
    public StudentLogic(UserRepository userRepository, StudentRepository studentRepository, LineRepository lineRepository){
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.lineRepository = lineRepository;
    }

    @Override
    public void createStudent(StudentDTO studentDTO) {

        Student student = new Student();
        User user = userRepository.findById(studentDTO.getUsername()).orElse(null);

        if(user !=  null){

            student.setName(studentDTO.getName());
            student.setSurname(studentDTO.getSurname());
            student.setUniversity(studentDTO.getUniversity());
            student.setUser(user);

            studentRepository.save(student);

        }

    }

    @Override
    public void chooseLines(LineInformationDTO lineInformationDTO) {

        Student student = studentRepository.findById(lineInformationDTO.getStudent().getId()).orElse(null);

        if(student == null){
            return;
        }

        List<BusPass> busPasses = new LinkedList<>();

        Calendar calendar = Calendar.getInstance();

        student.getBusPasses().forEach(b ->{
            if(b.getExpirationDate().compareTo(new Date(calendar.getTimeInMillis())) >= 0){
                busPasses.add(b);
            }
        });

        if(busPasses.size() == 0){

            Line line1, line2;
            line1 = lineRepository.findById(lineInformationDTO.getLine1().getId()).orElse(null);
            line2 = lineRepository.findById(lineInformationDTO.getLine2().getId()).orElse(null);

            if(line1 != null && line2 != null){

                BusPass busPass = new BusPass();
                busPass.addBusLine(line1);
                busPass.addBusLine(line2);

                student.addBusPass(busPass);

                studentRepository.save(student);

            }

        }

    }

    @Override
    public void toggleAutomaticLineSelection(StudentDTO studentDTO) {

        // Implement this in a sort of observable design pattern
        // Leave for when there is a clear idea of how to implement notifications in android,
        // since more than likely there will be some special kind of information that is required in order to notify

    }

}
