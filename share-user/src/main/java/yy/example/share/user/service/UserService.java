package yy.example.share.user.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Service;
import yy.example.share.common.exception.BusinessException;
import yy.example.share.common.exception.BusinessExceptionEnum;
import yy.example.share.common.util.JwtUtil;
import yy.example.share.common.util.showUtil;
import yy.example.share.user.domain.dto.LoginDTO;
import yy.example.share.user.domain.entity.User;
import yy.example.share.user.mapper.UserMapper;
import yy.example.share.user.resp.UserLoginResp;

import java.util.Date;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    public Long count(){
        return userMapper.selectCount(null);
    }
    public UserLoginResp login(LoginDTO loginDTO)
    {
        User userDB=userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));
        if(userDB==null)
        {
           throw new BusinessException(BusinessExceptionEnum.PHONE_NOT_EXIST);

        }
        if(!userDB.getPassword().equals(loginDTO.getPassword())){
            throw new BusinessException(BusinessExceptionEnum.PASSWORD_ERROR);
        }
       UserLoginResp userLoginResp=UserLoginResp.builder()
               .user(userDB)
               .build();
        String key= "InfinityX7";
        Map<String, Object> map = BeanUtil.beanToMap(userLoginResp);
        String token = JwtUtil.createToken(userLoginResp.getUser().getId(), userLoginResp.getUser().getPhone());
        userLoginResp.setToken(token);
        return userLoginResp;
    }

    public Long register(LoginDTO loginDTO)
    {
        User userDB=userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));
        if(userDB!=null)
        {
            throw new BusinessException(BusinessExceptionEnum.PHONE_EXIST);
        }
        User savedUser=User.builder()
                .id(showUtil.getSnowflakeNextId())
                .phone(loginDTO.getPhone())
                .password(loginDTO.getPassword())
                .nickname("新用户")
                .roles("user")
                .avatarUrl("https://i2.100024.xyz/2023/01/26/3exzjl.webp")
                .bonus(100)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userMapper.insert(savedUser);
        return savedUser.getId();
    }
}
