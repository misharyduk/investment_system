package com.ryduk.investment_system.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @GetMapping
    public void getMessage(@RequestParam("s") Integer s, HttpServletResponse response) throws IOException {
        if(s == 1) {
            response.setContentType("text/html");
            response.getWriter().println("<p style='background-color: green;'>No error</p>");
        }else
            response.getWriter().println("Error");
    }
}
