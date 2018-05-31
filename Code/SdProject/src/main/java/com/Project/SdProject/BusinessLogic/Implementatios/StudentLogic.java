package com.Project.SdProject.BusinessLogic.Implementatios;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineInformationDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StudentDTO;
import com.Project.SdProject.BusinessLogic.IStudentLogic;
import com.Project.SdProject.DataAccess.Entities.BusPass;
import com.Project.SdProject.DataAccess.Entities.Line;
import com.Project.SdProject.DataAccess.Entities.Student;
import com.Project.SdProject.DataAccess.Entities.User;
import com.Project.SdProject.DataAccess.Repositories.BusPassRepository;
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
    private BusPassRepository busPassRepository;
    private DtoFactory dtoFactory;

    @Autowired
    public StudentLogic(UserRepository userRepository, StudentRepository studentRepository, LineRepository lineRepository, BusPassRepository busPassRepository, DtoFactory dtoFactory){
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.lineRepository = lineRepository;
        this.busPassRepository = busPassRepository;
        this.dtoFactory = dtoFactory;
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

        String username =  lineInformationDTO.getStudent().getUsername();
        Student student = studentRepository.findByUser_Username(username);

        if(student == null){
            return;
        }

        List<BusPass> busPasses = new LinkedList<>();

        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTimeInMillis());

        student.getBusPasses().forEach(b ->{
            if(b.getExpirationDate().compareTo(currentDate) >= 0){
                busPasses.add(b);
            }
        });

        if(busPasses.size() == 0){

            Line line1, line2;
            line1 = lineRepository.findById(lineInformationDTO.getLine1().getLineNumber()).orElse(null);
            line2 = lineRepository.findById(lineInformationDTO.getLine2().getLineNumber()).orElse(null);

            if(line1 != null && line2 != null){

                BusPass busPass = new BusPass();
                busPass.addBusLine(line1);
                busPass.addBusLine(line2);
                busPass.setExpirationDate(Date.valueOf(currentDate.toLocalDate().plusMonths(1)));

                student.addBusPass(busPass);

                busPassRepository.save(busPass);
                //studentRepository.save(student);

            }

        }

    }

    @Override
    public void toggleAutomaticLineSelection(StudentDTO studentDTO) {

        // Implement this in a sort of observable design pattern
        // Leave for when there is a clear idea of how to implement notifications in android,
        // since more than likely there will be some special kind of information that is required in order to notify

    }

    @Override
    public StudentDTO get(String username) {
        return dtoFactory.createDTO(studentRepository.findByUser_Username(username));
    }

    @Override
    public List<LineDTO> getLines(StudentDTO studentDTO) {

        List<LineDTO> lineDTOS = new LinkedList<>();

        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTimeInMillis());

        try {

            studentRepository.findById(studentDTO.getId()).orElse(null).getBusPasses().stream()
                    .filter(b -> b.getExpirationDate().compareTo(currentDate) >= 0)
                    .forEach(b -> b.getLines()
                            .forEach(l -> lineDTOS.add(dtoFactory.createDTO(l))));

        }catch (NullPointerException e){
            return null;
        }

        return lineDTOS;

    }

}
