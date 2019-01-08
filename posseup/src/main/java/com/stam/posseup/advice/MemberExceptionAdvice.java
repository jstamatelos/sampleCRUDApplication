package com.stam.posseup.advice;

import com.stam.posseup.exception.MemberNameException;
import com.stam.posseup.exception.MemberNotFoundException;
import com.stam.posseup.exception.MemberPositionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MemberExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String memberNotFoundExceptionHandler(MemberNotFoundException notFoundException){
        return notFoundException.getMessage();

    }

    @ResponseBody
    @ExceptionHandler(MemberNameException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String memberNameExceptionHandler(MemberNameException nameException){
        return nameException.getMessage();

    }

    @ResponseBody
    @ExceptionHandler(MemberPositionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String memberPositionExceptionHandler(MemberPositionException positionException){
        return positionException.getMessage();

    }


}
