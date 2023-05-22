package com.example.dateweb.controller;

import com.example.dateweb.dto.MemberDto;
import com.example.dateweb.entity.Member;
import com.example.dateweb.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
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
            memberService.saveMember(member);
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

    // 회원 조회 컨트룰러
    @GetMapping(value = "/admin/memberlist")
    public String memberListForm(Model model){
        List<Member> members = memberService.selectAll();
        model.addAttribute("members",members);
        return "member/memberList";
    }

    @GetMapping(value = "/admin/delete")
    public String delete(Long id){
        memberService.deleteUser(id);
        return "redirect:/admin/memberlist";
    }

    @GetMapping(value = "/admin/modify")
    public String updateForm(Model model, Long id){
        Member member = memberService.selectUser(id);
        model.addAttribute("member", member);
        return "member/membermodify";
    }

        @PostMapping(value = "/admin/modify")
        public String updatePost(Member member) {

            memberService.updateUser(member);

            return "redirect:/admin/memberlist";
        }

    }
