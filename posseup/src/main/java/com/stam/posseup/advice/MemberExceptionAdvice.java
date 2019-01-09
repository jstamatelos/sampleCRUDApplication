package com.stam.posseup.advice;

import com.stam.posseup.exception.MemberNameException;
import com.stam.posseup.exception.MemberNotFoundException;
import com.stam.posseup.exception.MemberPositionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MemberExceptionAdvice {

    public static final Logger logger = LoggerFactory.getLogger(MemberExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String memberNotFoundExceptionHandler(MemberNotFoundException notFoundException){
        logger.warn("Member Id not found :: {}", notFoundException.getMessage());
        return notFoundException.getMessage();

    }

    @ResponseBody
    @ExceptionHandler(MemberNameException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String memberNameExceptionHandler(MemberNameException nameException){
        logger.warn("Member Name not found :: {}", nameException.getMessage());
        return nameException.getMessage();

    }

    @ResponseBody
    @ExceptionHandler(MemberPositionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String memberPositionExceptionHandler(MemberPositionException positionException){
        logger.warn("Member Name not found :: {}", positionException.getMessage());
        return positionException.getMessage();

    }


}
