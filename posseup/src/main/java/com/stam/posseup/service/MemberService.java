package com.stam.posseup.service;

import com.stam.posseup.advice.MemberExceptionAdvice;
import com.stam.posseup.entity.Member;
import com.stam.posseup.exception.MemberNameException;
import com.stam.posseup.exception.MemberNotFoundException;
import com.stam.posseup.exception.MemberPositionException;
import com.stam.posseup.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service Implementation class that abstracts the business logic away from the controller
@Service
public class MemberService {

    // Autowiring in the Member Repository so we can use it without instantiating multiple times
    @Autowired
    private MemberRepository repository;

    public List<Member> listAllMembers() {
        return repository.findAll();

    }

    // Todo :: Enhance with remaining special character and prohibited words in BlackListValidation class
    public Member createNewMember(Member newMember) {
        return repository.save(newMember);

    }

    public Member retrieveMemberById(Long id) {
        return repository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));

    }

    public Member updateMember(Member newMember, Long id) {
        return repository.findById(id).map(member -> {
            member.setName(newMember.getName());
            member.setPosition(newMember.getPosition());
            return repository.save(member);
            })
            .orElseGet(() -> {
                newMember.setId(id);
                return repository.save(newMember);
            });

    }

    public void deleteMember(Long id) {
        repository.deleteById(id);
    }


}
