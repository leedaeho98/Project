package com.example.dateweb.entity;

import com.example.dateweb.dto.MemberDto;
import com.example.dateweb.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(nullable = false, length = 16)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createUser(MemberDto userDto){
        Member member = new Member();
        member.setName(userDto.getName());
        member.setEmail(userDto.getEmail());
        member.setPhone(userDto.getPhone());
        member.setAge(userDto.getAge());
        member.setGender(userDto.getGender());
        member.setNickname(userDto.getNickname());
        member.setAddress(userDto.getAddress());
        member.setPassword(userDto.getPassword());
        member.setRole(Role.USER);
        return member;
    }
}
