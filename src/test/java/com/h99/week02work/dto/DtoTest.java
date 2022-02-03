package com.h99.week02work.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
public class DtoTest  {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    @DisplayName("유효성 검사")
    public void validateUser() {
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("bbbb!");
        signupRequestDto.setPassword("12345!");
        signupRequestDto.setPasswordCheck("1234");
        signupRequestDto.setEmail("test@test.test");

        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(signupRequestDto);

        Iterator<ConstraintViolation<SignupRequestDto>> iterator = violations.iterator();

        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<SignupRequestDto> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }
    }

    @Test
    @DisplayName("유효성 검사2")
    public void validateUser2() {
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("bb");
        signupRequestDto.setPassword("1234");
        signupRequestDto.setPasswordCheck("1234");
        signupRequestDto.setEmail("test@test.test");

        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(signupRequestDto);

        Iterator<ConstraintViolation<SignupRequestDto>> iterator = violations.iterator();

        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<SignupRequestDto> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }
    }

    @Test
    @DisplayName("유효성 검사3")
    public void validateUser3() {
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("bbbb");
        signupRequestDto.setPassword("1234");
        signupRequestDto.setPasswordCheck("1234");
        signupRequestDto.setEmail("test@test.test");

        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(signupRequestDto);

        Iterator<ConstraintViolation<SignupRequestDto>> iterator = violations.iterator();

        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<SignupRequestDto> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }
    }

    @Test
    @DisplayName("유효성 검사4")
    public void validateUser4() {
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("bbbb");
        signupRequestDto.setPassword("123");
        signupRequestDto.setPasswordCheck("123");
        signupRequestDto.setEmail("test@test.test");

        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(signupRequestDto);

        Iterator<ConstraintViolation<SignupRequestDto>> iterator = violations.iterator();

        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<SignupRequestDto> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }
    }
}
