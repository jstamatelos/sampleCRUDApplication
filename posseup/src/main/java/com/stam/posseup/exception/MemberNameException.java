package com.stam.posseup.exception;

import static com.stam.posseup.exception.ExceptionMessages.NAME_EXCEPTION_MESSAGE;

// Custom Exception class to handle invalid inputs
public class MemberNameException extends RuntimeException{

    public MemberNameException(String name) {
        super(NAME_EXCEPTION_MESSAGE + name);

    }

}
