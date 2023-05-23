package com.example.dateweb.entity;

import com.example.dateweb.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;


@SpringBootTest // 실제 운영환경에서 사용될 클래스들을 통합하여 테스트
@Transactional // 데이터베이스를  작업을 처리하던 중 오류가 발생했을 때 모든 작업을 원상태롣 돌림
@TestPropertySource(locations = "classpath:application-test.properties") // 테스트 H2데이터베이스

public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "gildong", roles = "USER") // 지정한 사용자가 로그인한 상태라고 가정한 테스트
    public void auditingTest(){
        Member newMember = new Member(); // 객체 생성
        memberRepository.save(newMember); // insert , update하는 역할을 한다

        em.flush(); // DB에 데이터를 반영한다
        em.clear(); // 영속성 컨텍스트(엔티티를 영구 저장하는 환경)를 지운다

        Member member = memberRepository.findById(newMember.getId())
                .orElseThrow(EntityNotFoundException::new);

        System.out.println("register time : " + member.getRegtime());
        System.out.println("update time : " + member.getUpdateTime());
        System.out.println("create time : " + member.getCreateBy());
        System.out.println("modified time : " + member.getModifiedBy());
        }
    }
