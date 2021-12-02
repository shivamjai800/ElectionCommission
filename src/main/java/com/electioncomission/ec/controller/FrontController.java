package com.electioncomission.ec.controller;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.SmsPojo;
import com.electioncomission.ec.model.TempOtp;
import com.electioncomission.ec.service.VisitService;
import com.electioncomission.ec.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.electioncomission.ec.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class FrontController {
    @Autowired
    PartService partService;

    @Autowired
    VoterService voterService;

    @Autowired
    VisitService visitService;

    @Autowired
    DaoAuthenticationProvider authenticationManager;

    @GetMapping("/login")
    public String login() {
        return "basic/login";
    }

    @PostMapping("/login")
    public String doLogin(HttpServletRequest request, RedirectAttributes redirectAttrs) {

        String redirect="redirect:/";
        redirect += "voteEntry";
        return redirect;

    }

    @GetMapping("/otp")
    public String otp(@ModelAttribute("mobileNumber") String mobileNumber, Model model) {
        model.addAttribute("mobileNumber", mobileNumber);
        return "basic/otp";
    }

    @PostMapping("/otp")
    public String setOtp(HttpServletRequest request, @Valid TempOtp otp, BindingResult result, Model model) {

        if (otp.getOtp().equals("000000")) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(otp.getMobileNumber(), otp.getOtp());
            authToken.setDetails(new WebAuthenticationDetails(request));
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/voteEntry";
        } else {
            model.addAttribute("error", "otp is invalid");
            return "basic/otp";
        }
    }

    @GetMapping("/bloDashboard")
    public String bloDashboard() {
        return "officer/bloDashboard";
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

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<String> partNames = this.partService.findAllPartNameByConstituencyId(1);
        model.addAttribute("partNames", partNames);
        return "officer/dashboard";
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


}
