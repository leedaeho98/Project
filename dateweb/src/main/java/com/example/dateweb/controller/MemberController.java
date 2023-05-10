package com.example.dateweb.controller;

import com.example.dateweb.dto.MemberDto;
import com.example.dateweb.entity.Member;
import com.example.dateweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;
    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberDto",new MemberDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String afterMember(MemberDto memberDto){
        Member member = Member.createUser(memberDto);
        memberService.saveMember(member);

        return "redirect:/";

    }
}
