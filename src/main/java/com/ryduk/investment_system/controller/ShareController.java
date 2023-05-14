package com.ryduk.investment_system.controller;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.EntityPageDto;
import com.ryduk.investment_system.model.Deposit;
import com.ryduk.investment_system.model.Share;
import com.ryduk.investment_system.service.DepositService;
import com.ryduk.investment_system.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/shares")
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @GetMapping("add")
    public ModelAndView addShareView(){
        return new ModelAndView("/addShare.jsp");
    }

    @PostMapping("add")
    public RedirectView addShare(@ModelAttribute @Valid Share share){

        Share shareFromDb = shareService.saveShare(share);
        ModelAndView mv = new ModelAndView("/addShare.jsp");

        if(shareFromDb == null){
            mv.addObject("errors", Collections.singletonList("Cannot add share"));
            return new RedirectView("/investments?s=0");
        }

        mv.addObject("share", shareFromDb);
        return new RedirectView("/investments?s=1");
    }

    @GetMapping
    public ModelAndView getAllShares(PaginationParameters parameters, @RequestParam(name = "s", required = false) Integer s){
        EntityPageDto<Share> allShares = shareService.getAllShares(parameters);

        ModelAndView mv = new ModelAndView("/showShares.jsp");
        mv.addObject("shares", allShares);

        if(s != null){
            if(s == 1){
                mv.addObject("success", 1);
            } else {
                mv.addObject("error", 0);
            }
        }
        return mv;
    }
//
//    @GetMapping
//    public ModelAndView getAllDeposits(PaginationParameters parameters, @RequestParam(name = "s", required = false) Integer s){
//        EntityPageDto<Deposit> allDeposits = depositService.getAllDeposits(parameters);
//
//        ModelAndView mv = new ModelAndView("/showDeposits.jsp");
//        mv.addObject("depositForm", new Deposit());
//        mv.addObject("deposits", allDeposits);
//
//        if(s != null){
//            if(s == 1)
//                mv.addObject("message", "Successfully invested");
//            else
//                mv.addObject("error", "Cannot invest");
//        }
//
//        return mv;
//    }

    @GetMapping("{id}")
    public ModelAndView getShare(@PathVariable("id") int id){
        Share share = shareService.getShare(id);

        ModelAndView mv = new ModelAndView("/showShare.jsp");
        if(share == null){
            mv.addObject("errors", Collections.singletonList("Share is not found"));
            return mv;
        }

        mv.addObject("share", share);
        return mv;
    }

    @DeleteMapping("{id}")
    public ModelAndView deleteShare(@PathVariable("id") int id){
        Share share = shareService.getShare(id);

        ModelAndView mv = new ModelAndView("/showShare.jsp");

        if(share != null && shareService.deleteShare(id))
            mv.addObject("isDeleted", true);
        else
            mv.addObject("errors", Collections.singletonList("Cannot delete share"));

        return mv;
    }
}
