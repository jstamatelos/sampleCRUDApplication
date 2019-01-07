package com.stam.posseup.advice;

import com.stam.posseup.exception.MemberExecption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MemberExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(MemberExecption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String memberNotFoundExceptionHandler(MemberExecption me){
        return me.getMessage();

    }

}
