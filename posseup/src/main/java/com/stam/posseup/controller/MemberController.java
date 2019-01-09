package com.stam.posseup.controller;

import com.stam.posseup.entity.Member;
import com.stam.posseup.exception.MemberNameException;
import com.stam.posseup.exception.MemberPositionException;
import com.stam.posseup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.stam.posseup.positions.PositionConstants.LEADER;
import static com.stam.posseup.positions.PositionConstants.MEMBER;
import static com.stam.posseup.positions.PositionConstants.PROSPECT;

@RestController
public class MemberController {

    // Autowiring in the MemberService so we can use it with instantiating multiple times
    @Autowired
    private MemberService service;

    // To get a list of all members
    @GetMapping("/members")
    public List<Member> listAll(){
        return service.listAllMembers();

    }

    // To add a new member to the posse
    @PostMapping("/members")
    public Member addNewMember(@Valid @RequestBody Member newMember) {

        if (!newMember.getPosition().equalsIgnoreCase(LEADER) && !newMember.getPosition().equalsIgnoreCase(MEMBER) && !newMember.getPosition().equalsIgnoreCase(PROSPECT)) {
            throw new MemberPositionException(newMember.getPosition());

        }

        return service.createNewMember(newMember);

    }

    // To get a member by id
    @GetMapping("/members/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return service.retrieveMemberById(id);

    }

    // To edit a Members Role or name
    @PutMapping("/members/{id}")
    public Member editMember(@Valid @RequestBody Member newMember, @PathVariable Long id) {
        return service.updateMember(newMember,id);

    }

    // To delete a Member by id
    @DeleteMapping("/members/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        service.deleteMember(id);

    }



}
