package com.electioncomission.ec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ro")
public class roController {
    @RequestMapping("/dashboard")
    public String getBloDashboard(Model model)
    {
        model.addAttribute("role","ro");
        return "officer/ro/dashboard";
    }

    @RequestMapping("/voteEntry")
    public String getBloVoteEntry(Model model)
    {
        model.addAttribute("role","ro");
        return "officer/ro/voteEntry";
    }
}
