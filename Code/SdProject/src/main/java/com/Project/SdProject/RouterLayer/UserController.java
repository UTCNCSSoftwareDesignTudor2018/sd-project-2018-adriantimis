package com.Project.SdProject.RouterLayer;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.UserDTO;
import com.Project.SdProject.BusinessLogic.IUserLogic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(path = "/user")
public class UserController {

    private IUserLogic userLogic;

    @GetMapping(path = "/get")
    public @ResponseBody UserDTO get(@RequestParam String id){
        return userLogic.getUser(id);
    }

}
