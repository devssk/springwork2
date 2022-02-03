package com.h99.week02work.integration;

import com.h99.week02work.dto.SignupRequestDto;
import com.h99.week02work.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {
    @Autowired
    private Validator validatorInjected;
    UserService userService;

    @Test
    @DisplayName("닉네임 유효성 검사")
    void test1(){
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("bbbb!");
        signupRequestDto.setPassword("12345!");
        signupRequestDto.setPasswordCheck("1234");
        signupRequestDto.setEmail("test@test.test");

        Set<ConstraintViolation<SignupRequestDto>> validate = validatorInjected.validate(signupRequestDto);

        Iterator<ConstraintViolation<SignupRequestDto>> iterator = validate.iterator();
        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<SignupRequestDto> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }

    }
}
