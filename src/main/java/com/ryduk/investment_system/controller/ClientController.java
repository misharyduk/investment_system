package com.ryduk.investment_system.controller;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.DepositDao;
import com.ryduk.investment_system.dto.EntityPageDto;
import com.ryduk.investment_system.dto.ShareDao;
import com.ryduk.investment_system.model.*;
import com.ryduk.investment_system.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final FactoryService factoryService;
    private final InvestmentService investmentService;

    @GetMapping("add")
    public ModelAndView addClientView(@RequestParam(name = "a", required = false) Integer a){
        ModelAndView mv = new ModelAndView("/addClient.jsp");
        if(a != null){
            if(a == 1){
                mv.addObject("success", 1);
            } else{
                mv.addObject("error", 0);
            }
        }
        mv.addObject("client", new Factory());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView addClient(@ModelAttribute @Valid Factory factory){

        System.out.println(factory);
        Factory factory1 = factoryService.saveFactory(factory);

        Client client = new Client();
        client.setFactory(factory1);

        client.setRole("USER");
        Client clientFromDb = clientService.saveClient(client);
        ModelAndView mv = new ModelAndView();

        if(clientFromDb == null){
            mv.addObject("errors", Collections.singletonList("Cannot add client"));
            mv.setViewName("redirect:/clients/add?a=0");
            return mv;
        }

        mv.addObject("client", clientFromDb);
        mv.setViewName("redirect:/clients/" + clientFromDb.getId());
        return mv;
    }

    @GetMapping
    public ModelAndView getAllClients(PaginationParameters parameters){
        EntityPageDto<Client> allClients = clientService.getAllClients(parameters);

        ModelAndView mv = new ModelAndView("/showClients.jsp");
        mv.addObject("clients", allClients);
        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView getClient(@PathVariable("id") int id,
                                  @RequestParam(name = "s", required = false) Integer s){
        Client client = clientService.getClient(id);

        ModelAndView mv = new ModelAndView("/showClient.jsp");
        if(client == null){
            mv.addObject("errors", Collections.singletonList("Client is not found"));
            return mv;
        }

        List<DepositDao> deposits = client.getInvestments().stream()
                .filter(investment -> investment.getInvestment() instanceof Deposit)
                .map(investment -> DepositDao.builder()
                        .investmentId(investment.getId())
                        .term(investment.getTerm())
                        .amount(investment.getInvestmentAmount())
                        .profit(investmentService.calculateProfit(investment))
                        .investmentType("deposit")
                        .pureDeposit((Deposit) investment.getInvestment())
                        .build()
                )
                .toList();

        List<ShareDao> shares = client.getInvestments().stream()
                .filter(investment -> investment.getInvestment() instanceof Share)
                .map(investment -> ShareDao.builder()
                        .investmentId(investment.getId())
                        .term(investment.getTerm())
                        .amount(investment.getInvestmentAmount())
                        .profit(investmentService.calculateProfit(investment))
                        .investmentType("share")
                        .pureShare((Share) investment.getInvestment())
                        .build()
                )
                .toList();

        mv.addObject("client", client);
        mv.addObject("deposits", deposits);
        mv.addObject("shares", shares);

        if(s != null){
            if(s == 1)
                mv.addObject("success", 1);
            else
                mv.addObject("error", 0);
        }

        return mv;
    }

    @DeleteMapping("{id}")
    public ModelAndView deleteClient(@PathVariable("id") int id){
        Client client = clientService.getClient(id);

        ModelAndView mv = new ModelAndView("/showClients.jsp");

        if(client != null && clientService.deleteClient(id))
            mv.addObject("isDeleted", true);
        else
            mv.addObject("errors", Collections.singletonList("Cannot delete client"));

        return mv;
    }
}
