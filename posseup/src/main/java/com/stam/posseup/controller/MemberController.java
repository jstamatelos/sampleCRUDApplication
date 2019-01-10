package com.stam.posseup.controller;

import com.stam.posseup.entity.Member;
import com.stam.posseup.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    // Autowiring in the MemberService so we can use it without instantiating multiple times
    @Autowired
    private MemberService service;

    @ApiOperation(value = "Obtain a list of all members")
    @GetMapping("/members")
    public List<Member> listAll(){
        logger.info("Getting all members :: {} ", service.listAllMembers());
        return service.listAllMembers();

    }

    @ApiOperation(value = "Add a new member to the posse")
    @PostMapping("/members")
    public Member addNewMember(@Valid @RequestBody Member newMember) {
        logger.info("Creating new member");
        return service.createNewMember(newMember);

    }

    @ApiOperation(value = "Obtain a member by their id")
    @GetMapping("/members/{id}")
    public Member getMemberById(@PathVariable Long id) {
        logger.info("Getting memeber by id :: {}", service.retrieveMemberById(id));
        return service.retrieveMemberById(id);

    }

    @ApiOperation(value = "Update a member Role or Name by their id")
    @PutMapping("/members/{id}")
    public Member editMember(@Valid @RequestBody Member newMember, @PathVariable Long id) {
        logger.info("Updating memeber by id :: {} ", service.updateMember(newMember, id));
        return service.updateMember(newMember,id);

    }

    @ApiOperation(value = "Delete a member by their id")
    @DeleteMapping("/members/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        logger.info("Deleteing memeber by id");
        service.deleteMember(id);

    }

}
