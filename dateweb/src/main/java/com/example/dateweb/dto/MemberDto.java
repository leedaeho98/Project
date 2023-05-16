package com.example.dateweb.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

// 회원가입 DTO
@Getter
@Setter
public class MemberDto {

    @NotBlank(message = "이름은 필수 입력 값입니다")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다")
    @Pattern(regexp = "")
    private String password;

    @NotBlank(message = "전화번호는 필수 입력 값입니다")
    private String phone;

    private int age;

    @NotEmpty(message = "성별은 필수 입력 값입니다")
    private String gender;

    @NotBlank(message = "닉네임은 필수 입력 값입니다")
    private String nickname;

    @NotEmpty(message = "주소는 필수 입력 값입니다")
    private String address;
}
