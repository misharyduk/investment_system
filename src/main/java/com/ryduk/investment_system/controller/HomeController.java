package com.ryduk.investment_system.controller;

import com.ryduk.investment_system.model.Client;
import com.ryduk.investment_system.model.UserDetailsImpl;
import com.ryduk.investment_system.security.SecurityUtil;
import com.ryduk.investment_system.service.ClientService;
import com.ryduk.investment_system.service.SupportCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final SupportCenterService supportCenterService;
    private final ClientService clientService;

    @RequestMapping
    public ModelAndView getRoot(){
        return new ModelAndView("redirect:login");
    }

    @RequestMapping("home")
    public ModelAndView getHome(){
        ModelAndView modelAndView = new ModelAndView("/home.jsp");
        UserDetailsImpl userFromAuth = SecurityUtil.getUser();

        Client client = clientService.getClient(userFromAuth.getId());
        if(client == null){
            System.err.println("Client is not found");
        }
        String advice = supportCenterService.clientAnalysis(client);
        modelAndView.addObject("advice", advice);
        return modelAndView;
    }
}
