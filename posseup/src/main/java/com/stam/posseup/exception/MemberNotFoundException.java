package com.stam.posseup.exception;

import static com.stam.posseup.exception.ExceptionMessages.INVALID_ID_EXCEPTION_MESSAGE;

// Custom Exception class to handle invalid member Id
public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long id) {
        super(INVALID_ID_EXCEPTION_MESSAGE + id);

    }

}
