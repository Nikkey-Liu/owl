package cn.wan.owl.aspect;

import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.util.ExceptionUtil;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Object handleGlobalException(Throwable t) {
        return CommonResponse.failed(ExceptionUtil.getStackTrace(t));
    }
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_PDF_VALUE;
    }
}
