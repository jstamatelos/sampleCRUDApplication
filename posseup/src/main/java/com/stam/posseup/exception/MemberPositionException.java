package com.stam.posseup.exception;

import static com.stam.posseup.exception.ExceptionMessages.POSITION_EXCEPTION_MESSAGE;

public class MemberPositionException extends RuntimeException {

    public MemberPositionException(String position) {
        super(POSITION_EXCEPTION_MESSAGE + position);

    }
}
