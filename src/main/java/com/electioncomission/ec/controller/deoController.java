package com.electioncomission.ec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deo")
public class deoController {
    @RequestMapping("/dashboard")
    public String getBloDashboard(Model model)
    {
        model.addAttribute("role","deo");
        return "officer/deo/dashboard";
    }

    @RequestMapping("/voteEntry")
    public String getBloVoteEntry(Model model)
    {
        model.addAttribute("role","deo");
        return "officer/deo/voteEntry";
    }

}
