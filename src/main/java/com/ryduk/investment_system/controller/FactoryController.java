package com.ryduk.investment_system.controller;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.EntityPageDto;
import com.ryduk.investment_system.model.Client;
import com.ryduk.investment_system.model.Factory;
import com.ryduk.investment_system.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/factories")
@RequiredArgsConstructor
public class FactoryController {
    private final FactoryService factoryService;

    @GetMapping("addFactory")
    public ModelAndView addFactoryView(){
        return new ModelAndView("/addFactory.jsp");
    }

    @PostMapping("addFactory")
    public ModelAndView addFactory(@ModelAttribute @Valid Factory factory){

        Factory factoryFromDb = factoryService.saveFactory(factory);
        ModelAndView mv = new ModelAndView("/addFactory.jsp");

        if(factoryFromDb == null){
            mv.addObject("errors", Collections.singletonList("Cannot add factory"));
            return mv;
        }

        mv.addObject("factory", factoryFromDb);
        return mv;
    }

    @GetMapping
    public ModelAndView getAllFactories(PaginationParameters parameters){
        List<Factory> allFactories = factoryService.getAllFactories();

        ModelAndView mv = new ModelAndView("/showFactories.jsp");
        mv.addObject("factories", allFactories);
        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView getFactory(@PathVariable("id") int id){
        Factory factory = factoryService.getFactory(id);

        ModelAndView mv = new ModelAndView("/showFactory.jsp");
        if(factory == null){
            mv.addObject("errors", Collections.singletonList("Factory is not found"));
            return mv;
        }

        mv.addObject("factory", factory);
        return mv;
    }

    @DeleteMapping("{id}")
    public ModelAndView deleteFactory(@PathVariable("id") int id){
        Factory factory = factoryService.getFactory(id);

        ModelAndView mv = new ModelAndView("/showFactories.jsp");

        if(factory != null && factoryService.deleteFactory(id))
            mv.addObject("isDeleted", true);
        else
            mv.addObject("errors", Collections.singletonList("Cannot delete factory"));

        return mv;
    }
}
