package com.electioncomission.ec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("")
public class FrontController {

    @GetMapping("/login")
    public String login()
    {
     return "basic/login";
    }

    @GetMapping("/otp")
    public String otp()
    {
        return "basic/otp";
    }
    @GetMapping("/bloDashboard")
    public String bloDashboard()
    {
        return "officer/bloDashboard";
    }

    @GetMapping("/voteEntry")
    public String voteEntry()
    {
        return "officer/voteEntry";
    }

    @GetMapping("/reports")
    public String reports()
    {
        return "officer/reports";
    }

    @GetMapping("/dashboard")
    public String adminModule()
    {
        return "officer/dashboard";
    }


}
