package com.h99.week02work.controller;

import com.h99.week02work.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String goHome(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return "index";
        }else {
            model.addAttribute("username", userDetails.getUsername());
            return "index";
        }
    }


    @GetMapping("/detail")
    public String goDetail(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return "detail";
        }else {
            model.addAttribute("username", userDetails.getUsername());
            return "detail";
        }
    }

    @GetMapping("/new")
    public String goNew(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return "new";
        }else{
            model.addAttribute("username",userDetails.getUsername());
            return "new";
        }

    }


}
