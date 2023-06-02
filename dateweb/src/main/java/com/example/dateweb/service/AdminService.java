package com.example.dateweb.service;

import com.example.dateweb.entity.Member;
import com.example.dateweb.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//  관리자 권한에 대한  Service
@Service
@Transactional

public class AdminService {
    @Autowired
    MemberRepository memberRepository;

    // 전체 회원 조회
    public List<Member> selectAll(){
        List<Member> memberList = memberRepository.findAll();
        return memberList;
    }

    // id로 회원 조회
    public Member selectUser(Long id){
        Member member = memberRepository.findById(id).get();
        return member;
    }

    // 회원 수정
    public void updateUser(Member member){
        memberRepository.save(member);
    }

    // 회원 삭제
    public void deleteUser(Long id){
        memberRepository.deleteById(id);
    }


}
