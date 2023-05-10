package com.example.dateweb.service;

import com.example.dateweb.entity.Member;
import com.example.dateweb.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public Member saveMember(Member member){
        duplication(member);
        return memberRepository.save(member);

    }

    private void duplication(Member member){
        Member find = memberRepository.findByEmail(member.getEmail()); // 중복된 회원(이메일)이 있는지 검사
        if(find != null){
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }


}
