package com.ryduk.investment_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    @GetMapping
    public ModelAndView loginView(@RequestParam(name = "a", required = false) Integer a){
        ModelAndView mv = new ModelAndView("/login.jsp");
        if(a != null){
            if(a == 1){
                mv.addObject("success", 1);
            } else{
                mv.addObject("error", 0);
            }
        }
        return mv;
    }
}
