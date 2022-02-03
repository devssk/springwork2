package com.h99.week02work.service;

import com.h99.week02work.dto.SignupRequestDto;
import com.h99.week02work.model.User;
import com.h99.week02work.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto requestDto){
        //닉네임 중복 확인
        validUsername(requestDto);
        String username = requestDto.getUsername();

        //비밀번호 일치 및 안에 닉네임 체크
        validPassword(requestDto);

        //패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());


        String email = requestDto.getEmail();

        User user = new User(username, password, email);
        userRepository.save(user);
    }

    public void validPassword(SignupRequestDto requestDto) {
        //비밀번호 일치
        if(!requestDto.getPassword().equals(requestDto.getPasswordCheck())){
            throw new IllegalArgumentException("비밀번호를 일치 시켜 주세요!");
        }
        //비밀번호 안에 닉네임 체크
        if(requestDto.getPassword().contains(requestDto.getUsername())){
            throw new IllegalArgumentException("비밀번호 안에 닉네임이 들어가면 안됩니다!");
        }
    }

    public void validUsername(SignupRequestDto requestDto) {
        //ID중복 확인
        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임이 사용중입니다.");
        }
    }

    public Map<String, String> validateHandling(Errors errors){
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
