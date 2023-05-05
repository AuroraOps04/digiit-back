package com.example.demo.configuration;
import cn.dev33.satoken.exception.NotLoginException;
import com.example.demo.domain.Response;
import com.example.demo.domain.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @author AuroraOps04
 * @date 2022/3/15 14:22:09
 * @description
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Response allExceptionHandle(Exception e) {
        log.error("Server Error", e);
        return Response.builder()
                .success(false)
                .errorCode("500")
                .showType(2)
                .errorMessage(e.getMessage())
                .build();
    }



    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Response paramsErrorHandler(Exception e) {

        BindingResult bindingResult = null;
        String message = "Params Error";
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();

        }
        if (bindingResult != null) {
            final List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (allErrors.size() > 0) {
                final ObjectError objectError = allErrors.get(0);
                message = String.format("%s error: %s", objectError.getObjectName(), objectError.getDefaultMessage());
            }
        }
        log.info(message);
        return ResponseGenerator.businessError(message);
    }

    // 这个异常一般是发生在用 @RequestBody 接受参数时, 请求体为空, 也有可能是请求体转换失败
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(Exception e) {
        log.warn(e.getLocalizedMessage());
        return ResponseGenerator.businessError("参数未传或者参数格式错误");
    }


    @ExceptionHandler({NotLoginException.class })
    public Response handleNoLogin(){
        return ResponseGenerator.noLogin();
    }


}
