package com.electioncomission.ec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ceo")
public class ceoController {
    @RequestMapping("/dashboard")
    public String getBloDashboard(Model model)
    {
        model.addAttribute("role","ceo");
        return "officer/ceo/dashboard";
    }
    @RequestMapping("/voteEntry")
    public String getBloVoteEntry(Model model)
    {
        model.addAttribute("role","ceo");
        return "officer/ceo/voteEntry";
    }
}
