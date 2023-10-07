package yy.example.share.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import yy.example.share.common.exception.BusinessException;
import yy.example.share.common.exception.BusinessExceptionEnum;
import yy.example.share.user.domain.dto.LoginDTO;
import yy.example.share.user.domain.entity.User;
import yy.example.share.user.mapper.UserMapper;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    public Long count(){
        return userMapper.selectCount(null);
    }
    public User login(LoginDTO loginDTO)
    {
        User userDB=userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));
        if(userDB==null)
        {
           throw new BusinessException(BusinessExceptionEnum.PHONE_NOT_EXIST);

        }
        if(!userDB.getPassword().equals(loginDTO.getPassword())){
            throw new BusinessException(BusinessExceptionEnum.PASSWORD_ERROR);
        }
        return userDB;
    }
}
