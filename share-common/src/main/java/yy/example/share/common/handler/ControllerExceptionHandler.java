package yy.example.share.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import yy.example.share.common.exception.BusinessException;
import yy.example.share.common.resp.CommonResp;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp<?> exceptionHandler(BusinessException e){
        CommonResp<?> commonResp=new CommonResp<>();
        log.error("系统异常",e);
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getDesc());
        return commonResp;
    }
}
