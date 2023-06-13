package com.example.dateweb.service;

import com.example.dateweb.entity.Member;
import com.example.dateweb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        duplication(member); // 중복 회원 검사
        return memberRepository.save(member);
    }

    private void duplication(Member member){
        Member find = memberRepository.findByEmail(member.getEmail()); // 중복된 회원(이메일)이 있는지 검사
        if(find != null){
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }


    // 로그인 할 유저의 email을 받아서 반환해주는 메서드
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null){ // 로그인할 유저의 email이 없다면 던져라
            throw new UsernameNotFoundException(email);
        }

        return User.builder() // User객체를 생성하기 위해서 생성자로 로그인 할 유저의 이메일과 비밀번호 권항을 파라미터로 넘겨준다
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
