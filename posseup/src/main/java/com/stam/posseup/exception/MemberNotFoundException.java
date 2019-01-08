package com.stam.posseup.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long id) {
        super("Sorry Partner, Member with id " + id + " does not exist");

    }

}
