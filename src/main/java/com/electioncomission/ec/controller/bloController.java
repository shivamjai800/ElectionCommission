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
@RequestMapping("/blo")
public class bloController {
    @Autowired
    UsersService usersService;

    @Autowired
    PartService partService;

    @RequestMapping("/dashboard")
    public String getBloDashboard(Model model, Principal principal)
    {
        if(principal.getName() != null) {
            String mobileNumber = principal.getName();
            Users currUser = this.usersService.findUsersByMobileNumber(mobileNumber);
            model.addAttribute("role", currUser.getUserRole());
            model.addAttribute("partId", currUser.getPartId());
            String partName = this.partService.findPartByPartId(currUser.getPartId()).getPartName();
            model.addAttribute("partName", partName);
        }
        return "officer/blo/dashboard";
    }

    @RequestMapping("/voteEntry")
    public String getBloVoteEntry(Model model)
    {
        model.addAttribute("role","blo");
        return "officer/blo/voteEntry";
    }

    @RequestMapping("/reports")
    public String getBloReports(Model model, Principal principal)
    {
        if(principal.getName() != null) {
            String mobileNumber = principal.getName();
            Users currUser = this.usersService.findUsersByMobileNumber(mobileNumber);
            model.addAttribute("role", currUser.getUserRole());
            model.addAttribute("partId", currUser.getPartId());
            String partName = this.partService.findPartByPartId(currUser.getPartId()).getPartName();
            model.addAttribute("partName", partName);
        }
        return "officer/blo/reports";
    }
}
