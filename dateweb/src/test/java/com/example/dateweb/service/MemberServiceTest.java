package com.example.dateweb.service;

import com.example.dateweb.dto.MemberDto;
import com.example.dateweb.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {
    @Autowired
    AuthService authService;

    @Autowired
    AdminService adminService;

    @Autowired
    PasswordEncoder passwordEncoder;


    public Member createMember(){
        MemberDto memberDto = new MemberDto();
        memberDto.setName("이대호");
        memberDto.setEmail("tlkj1633@naver.com");
        memberDto.setPassword("123456789");
        memberDto.setPhone("010-8754-2340");
        memberDto.setAge(100);
        memberDto.setGender("남자");
        memberDto.setNickname("레구어가");
        memberDto.setAddress("동탄");
        return Member.createUser(memberDto, passwordEncoder);
    }


    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        Member member = createMember();
        Member savedMember = authService.saveMember(member);

        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getPhone(), savedMember.getPhone());
        assertEquals(member.getAge(), savedMember.getAge());
        assertEquals(member.getGender(), savedMember.getGender());
        assertEquals(member.getNickname(), savedMember.getNickname());
        assertEquals(member.getAddress(), savedMember.getAddress());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getRole(), savedMember.getRole());

    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void loginDuplication(){
        Member member1 = createMember();
        Member member2 = createMember();
        authService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            authService.saveMember(member2);
        });

        assertEquals("이미 가입된 회원입니다", e.getMessage());

    }

    @Test
    public void updateUser(){
        Member member = createMember();
        member.setGender("여자");
        member.setAddress("안녕하세요");
        adminService.updateUser(member);

        };
}