package com.example.dateweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member_img")
@Getter @Setter
public class MemberImg {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 식별값

    private String imgName; // 이미지명

    private String imgUrl; // 이미지경로

    @ManyToOne // 여러사진이 하나의 회원에 담긴다
    @JoinColumn(name = "member_id")
    private Member member;

}
