package com.example.dateweb.controller;

import com.example.dateweb.dto.MemberDto;
import com.example.dateweb.entity.Member;
import com.example.dateweb.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// 회원가입 및 로그인 페이지 컨트룰러

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 페이지 컨트룰러
    @GetMapping(value = "/members/new")
    public String memberForm(Model model){
        model.addAttribute("memberDto",new MemberDto());
        return "/member/memberForm";
    }


    // 회원가입 성공했을떄 응답
    @PostMapping(value = "/members/new")
    public String afterMember(@Valid MemberDto memberDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){ // 검사 후 결과는 bidningResult에 담는다 , 에러가 있으면 회원 가입페이지로 이동
            return "member/memberForm";
        }
        try {
            Member member = Member.createUser(memberDto, passwordEncoder);
            authService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage" ,e.getMessage()); // 회원 가입 시 중복 회원 가입 예외가 발생하면 에러메시지를 뷰로 전달
            return "member/memberForm";
        }
        
        return "redirect:/";
    }

    // 로그인 페이지 컨트룰러
    @GetMapping(value = "/members/login")
    public String memberLoginForm(){
        return "member/memberLoginForm";
    }

    // 로그인 실패했을때
    @GetMapping(value = "/members/login/error")
    public String loginError(Model model){
        model.addAttribute("errorMessage","아이디 또는 비밀번호를 확인해주세요");
        return "member/memberLoginForm";
    }

}
