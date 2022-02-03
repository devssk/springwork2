package com.h99.week02work.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.h99.week02work.dto.SignupRequestDto;
import com.h99.week02work.model.User;
import com.h99.week02work.security.UserDetailsImpl;
import com.h99.week02work.service.KakaoUserService;
import com.h99.week02work.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    //회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null){
            return "login";
        }else {
            model.addAttribute("username", userDetails.getUsername());
            return "login";
        }
    }
    //회원가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return "signup";
        }else {
            model.addAttribute("username", userDetails.getUsername());
            return "signup";
        }
    }
    //회원가입
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model){
        //유저네임, 패스워드, 이메일이 유효한지 유효성검사
        if(errors.hasErrors()){
            //회원가입 오류시 기존내용을 유지하려고 했으나 오류로인해 쓰진않음....
            model.addAttribute("requestDto", requestDto);
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }
            return "signup";
        }
        //중복된 닉네임인지 비밀번호가 일치하는지 비밀번호안에 닉네임이 있는지 검사
        try{ userService.registerUser(requestDto); }
        catch (Exception error){
            model.addAttribute("errorMessage", error.getMessage());
            return "signup";
        }
        return "redirect:/user/login";
    }

    //카카오로그인
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}
