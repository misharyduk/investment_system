package com.ryduk.investment_system.controller;

import com.ryduk.investment_system.model.Client;
import com.ryduk.investment_system.service.ClientService;
import com.ryduk.investment_system.service.SupportCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("/center")
@RequiredArgsConstructor
public class SupportCenterController {

    private final SupportCenterService supportService;
    private final ClientService clientService;

    @GetMapping("{id}/clientAnalysis")
    public ModelAndView getClientAnalysis(@PathVariable("id") int id){
        ModelAndView mv = new ModelAndView("/advices.jsp");
        Client client = clientService.getClient(id);
        if(client == null){
            mv.addObject("error", Collections.singletonList("Cannot find client"));
            return mv;
        }
        String advice = supportService.clientAnalysis(client);
        mv.addObject("advice", advice);
        return mv;
    }
}
