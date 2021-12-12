package com.electioncomission.ec.controller;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.Enums;
import com.electioncomission.ec.entity.*;
import com.electioncomission.ec.model.ReportFilter;
import com.electioncomission.ec.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Controller
public class FrontController {
    @Autowired
    PartService partService;

    @Autowired
    VoterService voterService;

    @Autowired
    UsersService usersService;

    @Autowired
    VisitService visitService;

    @Autowired
    DistrictService districtService;

    @Autowired
    ConstituencyService constituencyService;

    @Autowired
    DaoAuthenticationProvider authenticationManager;

    @GetMapping("/login")
    public String login(Principal principal) {


        String redirectURL;
        if (principal == null) {
            redirectURL = "basic/login";
        }
        else redirectURL = "redirect:/dashboard";
        return redirectURL;
    }

    @GetMapping("/logoutt")
    public String dologout()
    {
        System.out.println("Here is the logout");
        return "basic/logout";
    }

    @GetMapping("/otp")
    public String otp(@ModelAttribute("mobileNumber") String mobileNumber, Model model) {
        model.addAttribute("mobileNumber", mobileNumber);
        return "basic/otp";
    }

    @GetMapping("/dashboard")
    public String bloDashboard(Model model, Principal principal) {
        if(principal==null)
        {
            return "redirect:/login";
        }
        else
        {
            String userId = principal.getName();
            Users users = this.usersService.findUsersByUserId(Integer.parseInt(userId));
            model.addAttribute("role", users.getUserRole());
            model.addAttribute("userName", users.getFirstName() + " " + users.getLastName());
            if(users.getUserRole().equals("BLO")) {
                model.addAttribute("partId", users.getPartId());
                String partName = this.partService.findPartByPartId(users.getPartId()).getPartName();
                model.addAttribute("partName", partName);
            } else if(users.getUserRole().equals("RO")) {
                model.addAttribute("constituencytId", users.getConstituencyId());
                List<Part> parts = this.partService.findPartsByConstituencyId(users.getConstituencyId());
                model.addAttribute("parts", parts);
            } else if(users.getUserRole().equals("DEO")) {
                model.addAttribute("districtId", users.getDistrictId());
                List<Constituency> constituencies = this.constituencyService.findAllConstituencyByDistrictId(users.getDistrictId());
                model.addAttribute("constituencies", constituencies);
            } else if(users.getUserRole().equals("CEO")) {
                List<District> districts = this.districtService.findAllDistricts();
                System.out.println(districts);
                model.addAttribute("districts", districts);
            }
            return "officer/" + users.getUserRole().toLowerCase(Locale.ROOT) + "/dashboard";
//            return "officer/dashboard";
        }
    }

    @GetMapping("/voteEntry")
    public String voteEntry(Model model) {
        return "officer/voteEntry";
    }

    @PostMapping("/voter/{category}/{epicNo}")
    public String getUserRecord(@PathVariable("category") String category, @PathVariable("epicNo") String epicNo, Model model) {
        ApiResponse<Voter> apiResponse = this.voterService.findVoterByEpicNoWhenCategory(epicNo, category);
        if (apiResponse.getData() != null) {
            model.addAttribute("voter", apiResponse.getData());
        } else {
            model.addAttribute("error", apiResponse.getApiError().getSubMessage());
        }
        return "officer/voteEntry";
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        List<String> partNames = this.partService.findAllPartNameByConstituencyId(1);
        model.addAttribute("partNames", partNames);
        return "officer/reports";
    }


    @PostMapping("/reports")
    public String getReportsByCriteria(@RequestBody ReportFilter reportFilter, Model model)
    {
//        System.out.println(reportFilter);
        Page<Visit> visits = this.visitService.getVisitsByCriteria(reportFilter,1);
        model.addAttribute("visits",visits);
        return "officer/reports";
    }

    @PostMapping("/visit")
    public String addVisit(@Valid @ModelAttribute("visit") Visit visit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "");
            bindingResult.getAllErrors().forEach(e -> {
                model.addAttribute("error", model.getAttribute("error") + " \n " + e.toString());
            });
        } else {
            System.out.println(visit);
            ApiResponse<Visit> apiResponse = this.visitService.addVoterVisit(visit, visit.getVoterEpicNo());
            if (apiResponse.getHttpStatus() == HttpStatus.EXPECTATION_FAILED)
                model.addAttribute("error", apiResponse.getApiError().getMessage());
            else if (apiResponse.getHttpStatus() == HttpStatus.OK) {
                model.addAttribute("success", "Visit added successfully");
            }
        }
        return "officer/voteEntry";
    }

    @GetMapping("/admin")
    public String loadAdmin(Principal principal, Model model)
    {
        Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));

        if(users.getUserRole().equals(Enums.UsersRole.RO.getValue()))
        {

            List<Users> bloList = this.usersService.findBloByConstituencyIdAndKeyword(principal,"").getData();
            model.addAttribute("bloList",bloList);
        }
        model.addAttribute("userName",users.getUserName());
        model.addAttribute("role",users.getUserRole());
        return "officer/"+users.getUserRole().toLowerCase()+"/admin";
    }
}
