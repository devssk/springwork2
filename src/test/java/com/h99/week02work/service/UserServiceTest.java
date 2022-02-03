package com.h99.week02work.service;

import com.h99.week02work.dto.SignupRequestDto;
import com.h99.week02work.model.User;
import com.h99.week02work.repository.UserRepository;
import jdk.nashorn.internal.runtime.ECMAErrors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("정상케이스")
    void registerUser() {
        String username;
        String password;
        String passwordCheck;
        String email;

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "king!",
                password = "1234",
                passwordCheck = "1234",
                email = "test@test.test"
        );

        UserService userService = new UserService(passwordEncoder, userRepository);
        userService.validUsername(signupRequestDto);
    }

    @Test
    @DisplayName("닉네임 중복검사")
    void registerUsername() {
        String username;
        String password;
        String passwordCheck;
        String email;
        Long kakaoId;

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "king",
                password = "1234",
                passwordCheck = "1234",
                email = "test@test.test"
        );

        User user = new User(
                username = "1",
                password = "12345",
                email = "test1@test.test",
                kakaoId = 12L
        );

        UserService userService = new UserService(passwordEncoder, userRepository);
        when(userRepository.findByUsername(signupRequestDto.getUsername())).thenReturn(Optional.of(user));
//        userService.validUsername(signupRequestDto);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validUsername(signupRequestDto);
        });

        assertEquals("중복된 닉네임이 사용중입니다.",exception.getMessage());
    }

    @Test
    @DisplayName("닉네임 중복검사2")
    void registerUsername2() {
        String username;
        String password;
        String passwordCheck;
        String email;
        Long kakaoId;

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "zero00sugar",
                password = "1234",
                passwordCheck = "1234",
                email = "test@test.test"
        );

        User user = new User(
                username = "zerosugar",
                password = "12345",
                email = "test1@test.test",
                kakaoId = 12L
        );

        UserService userService = new UserService(passwordEncoder, userRepository);
        when(userRepository.findByUsername(signupRequestDto.getUsername())).thenReturn(Optional.of(user));
//        userService.validUsername(signupRequestDto);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validUsername(signupRequestDto);
        });

        assertEquals("중복된 닉네임이 사용중입니다.",exception.getMessage());
    }

    @Test
    @DisplayName("비밀번호 일치")
    void registerUser_password(){
        String username;
        String password;
        String passwordCheck;
        String email;

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "king",
                password = "1234",
                passwordCheck = "12345",
                email = "test@test.test"
        );

        UserService userService = new UserService(passwordEncoder, userRepository);
//        userService.validPassword(signupRequestDto);

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            userService.validPassword(signupRequestDto);
        });

        assertEquals("비밀번호를 일치 시켜 주세요!", exception.getMessage());
    }

    @Test
    @DisplayName("비밀번호 일치2")
    void registerUser_password2(){
        String username;
        String password;
        String passwordCheck;
        String email;

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "king",
                password = "adbd1254",
                passwordCheck = "adbc1254",
                email = "test@test.test"
        );

        UserService userService = new UserService(passwordEncoder, userRepository);
//        userService.validPassword(signupRequestDto);

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            userService.validPassword(signupRequestDto);
        });

        assertEquals("비밀번호를 일치 시켜 주세요!", exception.getMessage());
    }



    @Test
    @DisplayName("비밀번호 안에 닉네임")
    void registerUser_passwordInUsername(){
        String username;
        String password;
        String passwordCheck;
        String email;

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "king",
                password = "1234king",
                passwordCheck = "1234king",
                email = "test@test.test"
        );


        UserService userService = new UserService(passwordEncoder, userRepository);

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            userService.validPassword(signupRequestDto);
        });

        assertEquals("비밀번호 안에 닉네임이 들어가면 안됩니다!", exception.getMessage());
    }

    @Test
    @DisplayName("비밀번호 안에 닉네임2")
    void registerUser_passwordInUsername2(){
        String username;
        String password;
        String passwordCheck;
        String email;

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "pepsiman",
                password = "12pepsiman99",
                passwordCheck = "12pepsiman99",
                email = "test@test.test"
        );

        UserService userService = new UserService(passwordEncoder, userRepository);

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            userService.validPassword(signupRequestDto);
        });

        assertEquals("비밀번호 안에 닉네임이 들어가면 안됩니다!", exception.getMessage());
    }
}