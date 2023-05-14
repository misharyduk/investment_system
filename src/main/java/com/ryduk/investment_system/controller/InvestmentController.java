package com.ryduk.investment_system.controller;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.EntityPageDto;
import com.ryduk.investment_system.exception.UserNotFoundException;
import com.ryduk.investment_system.model.*;
import com.ryduk.investment_system.security.SecurityUtil;
import com.ryduk.investment_system.service.ClientService;
import com.ryduk.investment_system.service.DepositService;
import com.ryduk.investment_system.service.InvestmentService;
import com.ryduk.investment_system.service.ShareService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Controller
@RequestMapping("/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService investmentService;
    private final DepositService depositService;
    private final ShareService shareService;
    private final ClientService clientService;

    @GetMapping("add")
    public ModelAndView addInvestmentView() {
        ModelAndView mv = new ModelAndView("/addInvestment.jsp");
        mv.addObject("investmentTypes", Arrays.asList("share", "deposit"));
        return mv;
    }

    @PostMapping("add")
    public ModelAndView addInvestment(@Param("investmentAmount") double investmentAmount, @Param("term") @DateTimeFormat(pattern = "yyyy-MM-dd") Date term,
                                      @Param("investmentType") String investmentType, @Param("investmentId") int investmentId) {

        int clientId = SecurityUtil.getUser().getId();

        ModelAndView mv = new ModelAndView();

        Investment investmentTransaction = Investment.builder()
                .investmentAmount(investmentAmount)
                .term(term)
                .build();

        InvestmentType investment;
        boolean isConfirmed = false;
        if (investmentType.equalsIgnoreCase("share")) {
            investment = shareService.getShare(investmentId);
            investmentTransaction.setInvestment(investment);
            isConfirmed = shareService.confirmInvestment(investmentTransaction);
            if(isConfirmed) {
                Share share = (Share) investment;
                share.getOwnershipHistory().add(clientService.getClient(clientId).getFactory());
                shareService.saveShare(share);
                mv.setViewName("redirect:/shares?s=1");
            }
        } else if (investmentType.equalsIgnoreCase("deposit")) {
            investment = depositService.getDeposit(investmentId);
            investmentTransaction.setInvestment(investment);
            System.out.println(investmentTransaction.getInvestment().getId());
            isConfirmed = depositService.confirmInvestment(investmentTransaction);
            mv.setViewName("redirect:/deposits?s=1");
        } else {
            System.err.println("Unknown investment type");
            mv.setViewName("redirect:/" + investmentType + "s?s=0");
            return mv;
        }

        if (!isConfirmed) {
            System.err.println("Cannot confirm investment");
            mv.setViewName("redirect:/" + investmentType + "s?s=0");
            return mv;
        }

        Investment investmentFromDb = investmentService.makeInvestment(investmentTransaction);

        if (investmentFromDb == null) {
            mv.setViewName("redirect:/" + investmentType + "s?s=0");
            return mv;
        }

        try {
            clientService.invest(clientId, investmentFromDb);
            mv.addObject("investment", investmentFromDb);
            return mv;
        } catch (UserNotFoundException e) {
            mv.setViewName("redirect:/" + investmentType + "s?s=0");
            return mv;
        }
    }

    @GetMapping
    public ModelAndView getAllInvestments(PaginationParameters parameters,
                                          @RequestParam(name = "s", required = false) Integer s) {
//        EntityPageDto<Investment> allInvestments = investmentService.getAllInvestments(parameters);

        long depositsCount = depositService.getCountOfAvailable();
        long sharesCount = shareService.getCountOfAvailable();

        ModelAndView mv = new ModelAndView("/addInvestment.jsp");
        mv.addObject("deposit", new Deposit());
        mv.addObject("share", new Share());
        mv.addObject("depositsCount", depositsCount);
        mv.addObject("sharesCount", sharesCount);

        if (s != null) {
            if (s == 1) {
                mv.addObject("success", 1);
            } else {
                mv.addObject("error", 0);
            }
        }
        return mv;
    }

    @GetMapping("user/{userId}")
    public ModelAndView getAllInvestmentsByUser(PaginationParameters parameters, @PathVariable("userId") int userId) {
        EntityPageDto<Investment> allInvestments = investmentService.getAllInvestmentsByUser(parameters, userId);

        ModelAndView mv = new ModelAndView("/showInvestments.jsp");
        mv.addObject("investments", allInvestments);
        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView getInvestment(@PathVariable("id") int id) {
        Investment investment = investmentService.getInvestment(id);

        ModelAndView mv = new ModelAndView("/showInvestment.jsp");
        if (investment == null) {
            mv.addObject("errors", Collections.singletonList("Investment is not found"));
            return mv;
        }

        double profit = investmentService.calculateProfit(investment);

        mv.addObject("profit", investment);
        mv.addObject("investment", investment);
        return mv;
    }

    @PostMapping("delete")
    public ModelAndView deleteInvestment(@RequestParam("investmentId") int investmentId,
                                         @RequestParam("investmentType") String investmentType) {

        int clientId = SecurityUtil.getUser().getId();
        Investment investment = investmentService.getInvestment(investmentId);

        ModelAndView mv = new ModelAndView("redirect:/clients/" + clientId + "?s=1");

        Client client = clientService.getClient(clientId);
        Investment investment2 = client.getInvestments().stream().filter(investment1 -> investment1.getId() == investmentId).findFirst().orElseThrow(() -> new IllegalArgumentException("No such investment"));
        client.getInvestments().remove(investment2);
        clientService.saveClient(client);

        boolean investmentDeleted = investmentService.deleteInvestment(investmentId);

        if (investment != null && investmentDeleted)
            if (investmentType.equalsIgnoreCase("deposit"))
                depositService.releaseInvestment(investment.getInvestment().getId());
            else
                shareService.releaseInvestment(investment.getInvestment().getId());
        else
            mv.setViewName("redirect:/clients/" + clientId + "?s=0");

        return mv;
    }
}
