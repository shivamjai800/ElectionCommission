package com.electioncomission.ec.controller;

import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.service.PartService;
import com.electioncomission.ec.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/ro")
public class roController {
    @Autowired
    UsersService usersService;

    @Autowired
    PartService partService;

    @RequestMapping("/dashboard")
    public String getRoDashboard(Model model, Principal principal)
    {
        if(principal.getName() != null) {
            String mobileNumber = principal.getName();
            Users currUser = this.usersService.findUsersByMobileNumber(mobileNumber);
            model.addAttribute("role", currUser.getUserRole());
            model.addAttribute("constituencytId", currUser.getConstituencyId());
            List<String> partNames = this.partService.findAllPartNameByConstituencyId(currUser.getConstituencyId());
            model.addAttribute("partNames", partNames);
        }
        return "officer/ro/dashboard";
    }

    @RequestMapping("/voteEntry")
    public String getRoVoteEntry(Model model)
    {
        model.addAttribute("role","ro");
        return "officer/ro/voteEntry";
    }

    @RequestMapping("/reports")
    public String getRoReports(Model model, Principal principal) {
        if(principal.getName() != null) {
            String mobileNumber = principal.getName();
            Users currUser = this.usersService.findUsersByMobileNumber(mobileNumber);
            model.addAttribute("role", currUser.getUserRole());
            model.addAttribute("constituencytId", currUser.getConstituencyId());
            List<String> partNames = this.partService.findAllPartNameByConstituencyId(currUser.getConstituencyId());
            model.addAttribute("partNames", partNames);
        }
        return "officer/ro/reports";
    }
}
