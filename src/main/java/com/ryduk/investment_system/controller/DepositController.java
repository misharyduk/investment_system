package com.ryduk.investment_system.controller;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.EntityPageDto;
import com.ryduk.investment_system.model.Deposit;
import com.ryduk.investment_system.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/deposits")
@RequiredArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @GetMapping("add")
    public ModelAndView addDepositView(){
        return new ModelAndView("/addDeposit.jsp");
    }

    @PostMapping("add")
    public RedirectView addDeposit(@ModelAttribute @Valid Deposit deposit){

        Deposit depositFromDb = depositService.saveDeposit(deposit);
        ModelAndView mv = new ModelAndView("/addDeposit.jsp");

        if(depositFromDb == null){
            mv.addObject("errors", Collections.singletonList("Cannot add deposit"));
            return new RedirectView("/investments?s=0");
        }

        mv.addObject("deposit", depositFromDb);
        return new RedirectView("/investments?s=1");
    }

    @GetMapping
    public ModelAndView getAllDeposits(PaginationParameters parameters, @RequestParam(name = "s", required = false) Integer s){
        EntityPageDto<Deposit> allDeposits = depositService.getAllDeposits(parameters);

        ModelAndView mv = new ModelAndView("/showDeposits.jsp");
        mv.addObject("depositForm", new Deposit());
        mv.addObject("deposits", allDeposits);

        if(s != null){
            if(s == 1)
                mv.addObject("success", 1);
            else
                mv.addObject("error", 0);
        }

        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView getDeposit(@PathVariable("id") int id){
        Deposit deposit = depositService.getDeposit(id);

        ModelAndView mv = new ModelAndView("/showDeposit.jsp");
        if(deposit == null){
            mv.addObject("errors", Collections.singletonList("Deposit is not found"));
            return mv;
        }

        mv.addObject("deposit", deposit);
        return mv;
    }

    @DeleteMapping("{id}")
    public ModelAndView deleteDeposit(@PathVariable("id") int id){
        Deposit deposit = depositService.getDeposit(id);

        ModelAndView mv = new ModelAndView("/showDeposit.jsp");

        if(deposit != null && depositService.deleteDeposit(id))
            mv.addObject("isDeleted", true);
        else
            mv.addObject("errors", Collections.singletonList("Cannot delete deposit"));

        return mv;
    }

}
