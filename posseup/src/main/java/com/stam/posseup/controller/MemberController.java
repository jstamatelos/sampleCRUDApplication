package com.stam.posseup.controller;

import com.stam.posseup.entity.Member;
import com.stam.posseup.exception.MemberExecption;
import com.stam.posseup.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    // Autowiring in the Member Repository so we can use it with instantiating multiple times
    @Autowired
    private MemberRepository repository;

    // To get a list of all members
    @GetMapping("/members")
    public List<Member> listAll(){
        return repository.findAll();

    }

    // To add a new member to the posse
    @PostMapping("/members")
    public Member addNewMember(@RequestBody Member newMember) {
        return repository.save(newMember);

    }

    // To get a member by id
    @GetMapping("/members/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(()-> new MemberExecption(id));

    }

    // To edit a Members Role or name
    @PutMapping("/members/{id}")
    public Member editMember(@RequestBody Member newMember, @PathVariable Long id) {
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

    // To delete a Member by id
    @DeleteMapping("/members/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        repository.deleteById(id);

    }



}
