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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, unique = true , length = 10)
    private String nickname;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createUser(MemberDto userDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(userDto.getName());
        member.setEmail(userDto.getEmail());
        member.setPhone(userDto.getPhone());
        member.setAge(userDto.getAge());
        member.setGender(userDto.getGender());
        member.setNickname(userDto.getNickname());
        String password = passwordEncoder.encode(member.getPassword()); // 비밀번호를 암호화 시킨다
        member.setPassword(password); // 암호화 시킨 번호를 저장한다
        member.setRole(Role.USER);
        return member;
    }
}
