package yy.example.share.user.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import yy.example.share.common.resp.CommonResp;
import yy.example.share.user.domain.dto.LoginDTO;
import yy.example.share.user.domain.entity.User;
import yy.example.share.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/count")
    public CommonResp<Long> count(){
        Long count= userService.count();
        CommonResp<Long> commonResp=new CommonResp<>();
        commonResp.setData(count);
        return commonResp;
    }
    @PostMapping("/login")
    public CommonResp<User> login(@Valid @RequestBody LoginDTO loginDTO){
        User user=userService.login(loginDTO);
        CommonResp<User> commonResp=new CommonResp<>();
        commonResp.setData(user);
        return commonResp;
    }
}
