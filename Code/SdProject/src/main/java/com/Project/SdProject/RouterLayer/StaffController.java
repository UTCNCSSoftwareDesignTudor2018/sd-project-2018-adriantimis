package com.Project.SdProject.RouterLayer;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.LineDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StaffDTO;
import com.Project.SdProject.BusinessLogic.DataTransferObjects.StudentDTO;
import com.Project.SdProject.BusinessLogic.IStaffLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(path = "/staff")
public class StaffController {

    private IStaffLogic staffLogic;

    @Autowired
    public StaffController(IStaffLogic staffLogic){
        this.staffLogic = staffLogic;
    }

    @PostMapping(path = "/create")
    public @ResponseBody void createStaff(@RequestBody StaffDTO staffDTO){

        staffLogic.createStaff(staffDTO);

    }

    @PostMapping(path = "/generateNfcCard")
    public @ResponseBody void generateNfcCard(StudentDTO studentDTO){

        staffLogic.generateNfcCard(studentDTO);

    }

    @GetMapping(path = "/checkPassValidity")
    public @ResponseBody List<LineDTO> checkPassValidity(@RequestParam String username){

        return staffLogic.checkPassValidity(username);

    }

    @GetMapping(path = "/get")
    public @ResponseBody StaffDTO get(@RequestParam String username){

        return staffLogic.get(username);

    }

}
