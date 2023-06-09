package com.example.dateweb.controller;

import com.example.dateweb.entity.Member;
import com.example.dateweb.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 관리자 권한 회원 정보 수정 및 삭제 컨트룰러

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 회원 조회 메서드
    @GetMapping(value = "/admin/memberlist")
    public String memberListForm(Model model){
        List<Member> members = adminService.selectAll();
        model.addAttribute("members",members);
        return "admin/memberList";
    }

    // 회원 삭제 메서드
    @GetMapping(value = "/admin/delete")
    public String delete(Long id){
        adminService.deleteUser(id);
        return "redirect:/admin/memberlist";
    }

    // 회원 수정 메서드
    @GetMapping(value = "/admin/modify")
    public String updateForm(Model model, Long id){
        Member member = adminService.selectUser(id);
        model.addAttribute("member", member);
        return "admin/membermodify";
    }

    @PostMapping(value = "/admin/modify")
    public String updatePost(Member member) {

        adminService.updateUser(member);

        return "redirect:/admin/memberlist";
    }

}
