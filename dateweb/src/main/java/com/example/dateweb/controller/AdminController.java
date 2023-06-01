package com.example.dateweb.controller;

import com.example.dateweb.entity.Member;
import com.example.dateweb.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;

    // 회원 조회 메서드
    @GetMapping(value = "/admin/memberlist")
    public String memberListForm(Model model){
        List<Member> members = memberService.selectAll();
        model.addAttribute("members",members);
        return "admin/memberList";
    }

    // 회원 삭제 메서드
    @GetMapping(value = "/admin/delete")
    public String delete(Long id){
        memberService.deleteUser(id);
        return "redirect:/admin/memberlist";
    }

    // 회원 수정 메서드
    @GetMapping(value = "/admin/modify")
    public String updateForm(Model model, Long id){
        Member member = memberService.selectUser(id);
        model.addAttribute("member", member);
        return "admin/membermodify";
    }

    @PostMapping(value = "/admin/modify")
    public String updatePost(Member member) {

        memberService.updateUser(member);

        return "redirect:/admin/memberlist";
    }

}
