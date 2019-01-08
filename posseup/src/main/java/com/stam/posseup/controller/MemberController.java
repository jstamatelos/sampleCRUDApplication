package com.stam.posseup.controller;

import com.stam.posseup.entity.Member;
import com.stam.posseup.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Member addNewMember(@RequestBody Member newMember) {
        return service.createNewMember(newMember);

    }

    // To get a member by id
    @GetMapping("/members/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return service.retrieveMemberById(id);

    }

    // To edit a Members Role or name
    @PutMapping("/members/{id}")
    public Member editMember(@RequestBody Member newMember, @PathVariable Long id) {
        return service.updateMember(newMember,id);

    }

    // To delete a Member by id
    @DeleteMapping("/members/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        service.deleteMember(id);

    }



}
