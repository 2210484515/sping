package yy.example.share.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import yy.example.share.common.exception.BusinessException;
import yy.example.share.common.resp.CommonResp;
import org.springframework.validation.BindException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResp<?> exceptionHandler(BindException e){
        CommonResp<?> commonResp=new CommonResp<>();
        log.error("校验异常: {}",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }
}
