package yy.example.share.user.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import yy.example.share.user.domain.dto.LoginDTO;
import yy.example.share.user.domain.entity.User;
import yy.example.share.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/count")
    public Long count(){
        return userService.count();
    }
    @PostMapping("/login")
    public User login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }
}
