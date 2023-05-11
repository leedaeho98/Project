package com.example.dateweb.controller;

import com.example.dateweb.dto.MemberDto;
import com.example.dateweb.entity.Member;
import com.example.dateweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

// 회원가입 컨트룰러
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
    public String afterMember(@Valid MemberDto memberDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){ // 검사 후 결과는 bidningResult에 담는다 , 에러가 있으면 회원 가입페이지로 이동
            return "member/memberForm";
        }
        try {
            Member member = Member.createUser(memberDto);
            memberService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage" ,e.getMessage()); // 회원 가입 시 중복 회원 가입 예외가 발생하면 에러메시지를 뷰로 전달
            return "member/memberForm";
        }
        
        return "redirect:/";

    }
}
