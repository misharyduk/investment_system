package com.ryduk.investment_system.controller;

import com.ryduk.investment_system.dto.SignUpClientDto;
import com.ryduk.investment_system.model.Client;
import com.ryduk.investment_system.model.Factory;
import com.ryduk.investment_system.service.ClientService;
import com.ryduk.investment_system.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final FactoryService factoryService;
    private final ClientService clientService;


    @GetMapping
    public ModelAndView registerView(@RequestParam(name = "a", required = false) Integer a){
        ModelAndView mv = new ModelAndView("/signup.jsp");
        if(a != null){
            if(a == 1){
                mv.addObject("success", 1);
            } else{
                mv.addObject("error", 0);
            }
        }
        mv.addObject("client", new SignUpClientDto());
        return mv;
    }

    @PostMapping
    public ModelAndView register(@ModelAttribute @Valid SignUpClientDto userDto){
        System.out.println("Start registering process");


        Factory factory = new Factory();
        factory.setName(userDto.getName());
        factory.setTypeOfProperty(userDto.getTypeOfProperty());
        factory.setAddress(userDto.getAddress());
        factory.setTelephone(userDto.getTelephone());
        Factory factory1 = factoryService.saveFactory(factory);
        System.out.println("Factory saved");

        Client client = new Client();
        client.setRole("USER");
        client.setUsername(userDto.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        client.setPassword(encoder.encode(userDto.getPassword()));
        client.setFactory(factory1);

        Client clientFromDb = clientService.saveClient(client);
        System.out.println("Client saved");

        ModelAndView mv = new ModelAndView();

        if(clientFromDb == null){
            mv.setViewName("redirect:/signup?a=0");
            return mv;
        }

        mv.setViewName("redirect:/login?a=1");
        return mv;
    }
}
