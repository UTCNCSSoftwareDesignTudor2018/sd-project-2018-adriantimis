package com.Project.SdProject.RouterLayer;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineInformationDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StudentDTO;
import com.Project.SdProject.BusinessLogic.IStudentLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(path = "/student")
public class StudentController {

    private IStudentLogic studentLogic;

    @Autowired
    public StudentController(IStudentLogic studentLogic){
        this.studentLogic = studentLogic;
    }

    @PostMapping(path = "/create")
    public @ResponseBody void createStudent(@RequestBody StudentDTO studentDTO){

        studentLogic.createStudent(studentDTO);

    }

    @PostMapping(path = "/chooseLines")
    public @ResponseBody void chooseLines(@RequestBody LineInformationDTO lineInformationDTO){

        studentLogic.chooseLines(lineInformationDTO);

    }

    @PostMapping(path = "/toggleAutomaticLineSelection")
    public @ResponseBody void toggleAutomaticLineSelection(@RequestBody StudentDTO studentDTO){

        studentLogic.toggleAutomaticLineSelection(studentDTO);

    }

    @GetMapping(path = "/get")
    public @ResponseBody StudentDTO get(@RequestParam String username){
        return studentLogic.get(username);
    }

    @PostMapping(path = "/getLines")
    public @ResponseBody List<LineDTO> getLines(@RequestBody StudentDTO studentDTO){
        return studentLogic.getLines(studentDTO);
    }

}
