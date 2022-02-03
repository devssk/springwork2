package com.h99.week02work.service;

import com.h99.week02work.dto.SignupRequestDto;
import com.h99.week02work.model.User;
import com.h99.week02work.repository.UserRepository;
import jdk.nashorn.internal.runtime.ECMAErrors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    //중복검사의 판별은 Optional로 되어있는걸 isPresent()로 판별한다.
    //따라서 isPresent()는 userRepository.findByUsername을 했을시 값을 1개 찾아오거나 null이다.
    //조건에 불러와지는게 아무것도 없으면 null이 되고 아무거나 불러와지면 중복값이 있는것으로 검사가된다.
    //이 조건은 null일때이고 결과 값이 Exception 에러메세지를 들고 오는 것이므로 에러메세지를 못들고 왔기때문에 실패가 된다.
//    @Test
//    @DisplayName("닉네임 중복검사-실패해야함")
//    void registerUsername() {
//        String username;
//        String password;
//        String passwordCheck;
//        String email;
//        Long kakaoId;
//
//        SignupRequestDto signupRequestDto = new SignupRequestDto(
//                username = "king",
//                password = "1234",
//                passwordCheck = "1234",
//                email = "test@test.test"
//        );
//
//        User user = new User(
//                username = null,
//                password = "12345",
//                email = "test1@test.test",
//                kakaoId = 12L
//        );
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            userService.validUsername(signupRequestDto);
//        });
//
//        assertEquals("중복된 닉네임이 사용중입니다.",exception.getMessage());
//
//    }


    //중복검사의 판별은 Optional로 되어있는걸 isPresent()로 판별한다.
    //따라서 isPresent()는 userRepository.findByUsername을 했을시 값을 1개 찾아오거나 null이다.
    //조건에 불러와지는게 아무것도 없으면 null이 되고 아무거나 불러와지면 중복값이 있는것으로 검사가된다.
    //이 조건은 userRepository.findByUsername을 햇을때 결과값을 들고왔으므로 isPresent()가 참이되었다.
    //따라서 Exception 에러메세지를 들고와져서 비교햇을때 맞으므로 성공이된다.
    //signupRequestDto의 username과 user의 username값을 비교하는것은 아니다.
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

        when(userRepository.findByUsername(signupRequestDto.getUsername())).thenReturn(Optional.of(user));
//        userService.validUsername(signupRequestDto);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validUsername(signupRequestDto);
        });

        assertEquals("중복된 닉네임이 사용중입니다.",exception.getMessage());
    }


    //이 테스트는 userRepository.findByUsername을 테스트한다.
    //userRepository.findByUsername을 해서 결과값이 불러와지는걸 테스트해야하지만 여기는 결과값을 임의로 넣는다.
    //결과 값을 임의로 넣었지만 결과 값 user의 username과 signupRequestDto의 username값은 다르므로 원래는 불러와지지 않는다.
    @Test
    @DisplayName("닉네임 중복검사3")
    void registerUsername3() {
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

        when(userRepository.findByUsername(signupRequestDto.getUsername())).thenReturn(Optional.of(user));

        Optional<User> found = userRepository.findByUsername(signupRequestDto.getUsername());

        assertNotEquals(found.get().getUsername(), signupRequestDto.getUsername());


    }


    //이 테스트는 userRepository.findByUsername을 테스트한다.
    //userRepository.findByUsername을 해서 결과값이 불러와지는걸 테스트해야하지만 여기는 결과값을 임의로 넣는다.
    //결과 값을 임의로 넣었지만 결과 값 user의 username과 signupRequestDto의 username값이 같은지 체크한다.
    @Test
    @DisplayName("닉네임 중복검사4")
    void registerUsername4() {
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
                username = "zero00sugar",
                password = "12345",
                email = "test1@test.test",
                kakaoId = 12L
        );
        when(userRepository.findByUsername(signupRequestDto.getUsername())).thenReturn(Optional.of(user));


        Optional<User> found = userRepository.findByUsername(signupRequestDto.getUsername());

        assertEquals(found.get().getUsername(), signupRequestDto.getUsername());

    }

    //비밀번호가 일치하지 않음
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

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            userService.validPassword(signupRequestDto);
        });

        assertEquals("비밀번호를 일치 시켜 주세요!", exception.getMessage());
    }

    //비밀번호가 일치하지 않음
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


        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            userService.validPassword(signupRequestDto);
        });

        assertEquals("비밀번호를 일치 시켜 주세요!", exception.getMessage());
    }

    //정상케이스?
    @Test
    @DisplayName("비밀번호 일치3")
    void registerUser_password3(){
        String username;
        String password;
        String passwordCheck;
        String email;

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "king",
                password = "adcd1254",
                passwordCheck = "adcd1254",
                email = "test@test.test"
        );



        userService.validPassword(signupRequestDto);



    }


    //비밀번호 뒤에 닉네임이 들어가있음
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


        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            userService.validPassword(signupRequestDto);
        });

        assertEquals("비밀번호 안에 닉네임이 들어가면 안됩니다!", exception.getMessage());
    }

    //비밀번호 안에 닉네임이 들어가있음
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

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            userService.validPassword(signupRequestDto);
        });

        assertEquals("비밀번호 안에 닉네임이 들어가면 안됩니다!", exception.getMessage());
    }

    //정상케이스?
    @Test
    @DisplayName("비밀번호 안에 닉네임3")
    void registerUser_passwordInUsername3(){
        String username;
        String password;
        String passwordCheck;
        String email;

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "pepsiman",
                password = "1299",
                passwordCheck = "1299",
                email = "test@test.test"
        );


        userService.validPassword(signupRequestDto);



    }
}