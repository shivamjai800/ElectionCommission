package com.electioncomission.ec.controller;

import com.electioncomission.ec.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FrontController {
    @Autowired
    PartService partService;

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
    public String reports(Model model)
    {
        List<String> partNames = this.partService.findAllPartNameByConstituencyId(1);
//        List<String> partNames = null;
        model.addAttribute("partNames", partNames);
        return "officer/reports";
    }

    @GetMapping("/dashboard")
    public String adminModule()
    {
        return "officer/dashboard";
    }


}
