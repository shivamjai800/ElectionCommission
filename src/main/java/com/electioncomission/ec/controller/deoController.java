package com.electioncomission.ec.controller;

import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.service.ConstituencyService;
import com.electioncomission.ec.service.PartService;
import com.electioncomission.ec.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/deo")
public class deoController {
    @Autowired
    UsersService usersService;

    @Autowired
    PartService partService;

    @Autowired
    ConstituencyService constituencyService;

    @RequestMapping("/dashboard")
    public String getDeoDashboard(Model model, Principal principal)
    {
        if(principal.getName() != null) {
            String mobileNumber = principal.getName();
            Users currUser = this.usersService.findUsersByMobileNumber(mobileNumber);
            model.addAttribute("role", currUser.getUserRole());
            model.addAttribute("districtId", currUser.getDistrictId());
            List<String> constituencyNames = this.constituencyService.findAllConstituencyNameByDistrictId(currUser.getDistrictId());
            model.addAttribute("constituencyNames", constituencyNames);
            HashMap<String, List<String>> partNamesPerConstituency = new HashMap<>();
            constituencyNames.forEach(c->partNamesPerConstituency.put(c, this.partService.findAllPartNameByConstituencyName(c)));
            model.addAttribute("partNamesPerConstituency", partNamesPerConstituency);
        }
        return "officer/ro/dashboard";
    }

    @RequestMapping("/voteEntry")
    public String getDeoVoteEntry(Model model)
    {
        model.addAttribute("role","deo");
        return "officer/deo/voteEntry";
    }

    @RequestMapping("/reports")
    public String getDeoReports(Model model, Principal principal) {
        if(principal.getName() != null) {
            String mobileNumber = principal.getName();
            Users currUser = this.usersService.findUsersByMobileNumber(mobileNumber);
            model.addAttribute("role", currUser.getUserRole());
            model.addAttribute("districtId", currUser.getDistrictId());
            List<String> constituencyNames = this.constituencyService.findAllConstituencyNameByDistrictId(currUser.getDistrictId());
            model.addAttribute("constituencyNames", constituencyNames);
            HashMap<String, List<String>> partNamesPerConstituency = new HashMap<>();
            constituencyNames.forEach(c->partNamesPerConstituency.put(c, this.partService.findAllPartNameByConstituencyName(c)));
            model.addAttribute("partNamesPerConstituency", partNamesPerConstituency);
        }
        return "officer/ro/reports";
    }

}
