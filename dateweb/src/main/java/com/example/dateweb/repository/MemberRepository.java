package com.example.dateweb.repository;

import com.example.dateweb.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email); // 중복된 회원(이메일)이 있는지 검사


    List<Member> findAll(); // 전체 회원 조회
    
    Optional<Member> findById(Long id); // 회원 조회

    void deleteById(Long id); // 회원 삭제






}
