package com.Project.SdProject.RouterLayer;

import com.Project.SdProject.BusinessLogic.DataTransferObjects.UserDTO;
import com.Project.SdProject.BusinessLogic.IUserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping(path = "/auth")
public class AuthenticationController {

    private IUserLogic userLogic;

    @Autowired
    public AuthenticationController(IUserLogic userLogic){
        this.userLogic = userLogic;
    }

    @PostMapping(path = "/register")
    public @ResponseBody boolean register(@RequestBody UserDTO userDTO){
        return userLogic.register(userDTO);
    }

    @PostMapping(path = "/login")
    public @ResponseBody String login(@RequestBody UserDTO userDTO){
        return userLogic.login(userDTO);
    }

}
