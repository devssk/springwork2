package com.h99.week02work.validator;

import com.h99.week02work.dto.SignupRequestDto;
import org.springframework.stereotype.Component;

//어노테이션으로 쓰지 않고 따로 만들었으나 쓸일은 없음....
@Component
public class Validator {
    public static void validateSignup(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();
        String email = requestDto.getEmail();
        if ( username == null ){
            throw new IllegalArgumentException("닉네임을 입력해 주세요.");
        }
        if ( username.length() < 3 || !username.matches("[a-z][A-Z][0-9]") ){
            throw new IllegalArgumentException("닉네임은 3자 이상, 알파벳과 숫자로 구성되어야 합니다.");
        }
        if( password == null ){
            throw new IllegalArgumentException("패스워드를 입력해 주세요");
        }
        if( password.length() < 4 || !password.matches("[a-z][A-Z][0-9]") ){
            throw new IllegalArgumentException("패스워드는 4자 이상, 알파벳과 숫자로 구성되어야 합니다.");
        }
        if( passwordCheck == null ){
            throw new IllegalArgumentException("패스워드를 입력해 주세요");
        }
        if( passwordCheck.length() < 4 || !passwordCheck.matches("[a-z][A-Z][0-9]") ){
            throw new IllegalArgumentException("패스워드는 4자 이상, 알파벳과 숫자로 구성되어야 합니다.");
        }
//        if( email == null ){
//            throw new IllegalArgumentException("이메일를 입력해 주세요");
//        }
//        if( email == ){
//            throw new IllegalArgumentException("이메일의 형식이 아닙니다.");
//        }
    }
}
