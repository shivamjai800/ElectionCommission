package com.electioncomission.ec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blo")
public class bloController {
    @RequestMapping("/dashboard")
    public String getBloDashboard(Model model)
    {
        model.addAttribute("role","blo");
        return "officer/blo/dashboard";
    }

    @RequestMapping("/voteEntry")
    public String getBloVoteEntry(Model model)
    {
        model.addAttribute("role","blo");
        return "officer/blo/voteEntry";
    }
}
