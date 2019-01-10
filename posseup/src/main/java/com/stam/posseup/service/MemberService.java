package com.stam.posseup.service;

import com.stam.posseup.entity.Member;
import com.stam.posseup.exception.MemberNotFoundException;
import com.stam.posseup.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.stam.posseup.exception.ValidationHelper.validateNewMemberPositionAndName;

// Service Implementation class that abstracts the business logic away from the controller
@Service
public class MemberService {

    public static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    // Autowiring in the Member Repository so we can use it without instantiating multiple times
    @Autowired
    private MemberRepository repository;

    public List<Member> listAllMembers() {
        return repository.findAll();

    }

    public Member createNewMember(Member newMember) {
        validateNewMemberPositionAndName(newMember);
        return repository.save(newMember);

    }

    public Member retrieveMemberById(Long id) {
        return repository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));

    }

    public Member updateMember(Member newMember, Long id) {
        validateNewMemberPositionAndName(newMember);
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
        Member memberIdToDelete = repository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        repository.deleteById(memberIdToDelete.getId());


    }


}
