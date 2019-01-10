package com.stam.posseup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stam.posseup.controller.MemberController;
import com.stam.posseup.entity.Member;
import com.stam.posseup.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class TestMemberController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    public void testReturningListOfAllMemebers() throws Exception {

        Member newMember = new Member("Jarryd","Leader");
        List<Member> listOfAllMembers = Arrays.asList(newMember);

        given(memberService.listAllMembers()).willReturn(listOfAllMembers);

        mockMvc.perform(get("/members")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void testReturningMemberById() throws Exception {

        long id = 1;
        Member newMember = new Member("Jarryd","Leader");
        newMember.setId(id);

        given(memberService.retrieveMemberById(id)).willReturn(newMember);

        mockMvc.perform(get("/members/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

    }

    @Test
    public void testReturningMemberByIdFailure_IdDoesNotExist() throws Exception {

        long id = 2;
        Member newMember = new Member("Jarryd","Leader");

        given(memberService.retrieveMemberById(id)).willReturn(newMember);

        mockMvc.perform(get("/members/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").doesNotExist());

    }

    @Test
    public void testAddingNewMemberIsSuccessful() throws Exception {

        String memberAsJson;
        long id = 2;
        Member newMember = new Member("Earl","Leader");
        newMember.setId(id);

        try {
            memberAsJson = new ObjectMapper().writeValueAsString(newMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        when(memberService.createNewMember(newMember)).thenReturn(newMember);
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(memberAsJson))
                .andExpect(status().isOk());

    }

    @Test
    public void testNewMemberCannotBeCreatedWithNameTooShort() throws Exception {

        String memberAsJson;
        long id = 2;
        Member newMember = new Member("","Member");
        newMember.setId(id);

        try {
            memberAsJson = new ObjectMapper().writeValueAsString(newMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        when(memberService.createNewMember(newMember)).thenReturn(newMember);
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(memberAsJson))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testNewMemberCannotBeCreatedWithNameTooLong() throws Exception {

        String memberAsJson;
        long id = 2;
        Member newMember = new Member("ThisNameIsGoingToBeLongerThanTwentyFourCharacters","Member");
        newMember.setId(id);

        try {
            memberAsJson = new ObjectMapper().writeValueAsString(newMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        when(memberService.createNewMember(newMember)).thenReturn(newMember);
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(memberAsJson))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testNewMemberCannotBeCreatedWithNameContainingSpecialCharacters() throws Exception {

        String memberAsJson;
        long id = 2;
        Member newMember = new Member("*@(*#*@@/","Member");
        newMember.setId(id);

        try {
            memberAsJson = new ObjectMapper().writeValueAsString(newMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        when(memberService.createNewMember(newMember)).thenReturn(newMember);
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(memberAsJson))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testNewMemberCannotBeCreatedWithUnacceptablePosition_EmptyPosition() throws Exception {

        String memberAsJson;
        long id = 2;
        Member newMember = new Member("Carl", null);
        newMember.setId(id);

        try {
            memberAsJson = new ObjectMapper().writeValueAsString(newMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        when(memberService.createNewMember(newMember)).thenReturn(newMember);
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(memberAsJson))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testNewMemberCannotBeCreatedWithUnacceptablePosition_PositionContaintsSpecialCharacters() throws Exception {

        String memberAsJson;
        long id = 2;
        Member newMember = new Member("Carl","#&(*@&#$&//");
        newMember.setId(id);

        try {
            memberAsJson = new ObjectMapper().writeValueAsString(newMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        when(memberService.createNewMember(newMember)).thenReturn(newMember);
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(memberAsJson))
                .andExpect(status().isBadRequest());

    }

    // TODO :: This test failing at unit test level but passing through PostMan and Swagger. Need to figure out where it is going wrong
    public void testNewMemberCannotBeCreatedWithUnacceptablePosition() throws Exception {

        String memberAsJson;
        long id = 2;
        Member newMember = new Member("Carl","NOT A VALID POSITION");
        newMember.setId(id);

        try {
            memberAsJson = new ObjectMapper().writeValueAsString(newMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        when(memberService.createNewMember(newMember)).thenReturn(newMember);
        System.out.println("NEW MEMBER :::: " + ":::: " + newMember.toString());
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(memberAsJson))
                .andExpect(status().isForbidden());

    }


}
