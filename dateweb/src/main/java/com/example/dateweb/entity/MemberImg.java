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
    @Column(name = "item_img_id")
    private Long id;

    private String imgUrl; // 이미지를 가져올 주소

    @ManyToOne // 여러개의 사진은 한명의 회원에 추가할수있다
    @JoinColumn(name = "member_id")
    private Member member;
    


}
