package com.stam.posseup.exception;

public class MemberExecption extends RuntimeException {

    public MemberExecption(Long id) {
        super("Sorry Partner, Member with id " + id + " does not exist");

    }

}
