package com.example.dateweb.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


//
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 어떤 메서드에서 참조가 가능하며, 인증객체를 어디서든 꺼내어 사용할 수 있다.
        String userId = "";
        if(authentication != null){
            userId = authentication.getName(); // 현재 로그인 한 사용자의 정보를 조회하여 사용자의 이름을 등록자와 수정자로 지정
        }
        return Optional.of(userId);
    }
}
