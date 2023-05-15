package com.example.dateweb.entity;

import com.example.dateweb.dto.MemberDto;
import com.example.dateweb.role.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

// 회원가입 테이블
@Entity
@Table(name = "member")
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private int age;

    private String gender;

    @Column(unique = true)
    private String nickname;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createUser(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());
        member.setPhone(memberDto.getPhone());
        member.setAge(memberDto.getAge());
        member.setGender(memberDto.getGender());
        member.setNickname(memberDto.getNickname());
        member.setAddress(memberDto.getAddress());
        String password = passwordEncoder.encode(memberDto.getPassword()); // 비밀번호를 암호화 시킨다
        member.setPassword(password); // 암호화 시킨 번호를 저장한다
        member.setRole(Role.USER); // 권한설정
        return member;
    }
}
