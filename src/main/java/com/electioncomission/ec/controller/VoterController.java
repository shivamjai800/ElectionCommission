package com.electioncomission.ec.controller;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    VoterService voterService;

//    @GetMapping("/{category}/{epicNo}")
//    public ApiResponse<Voter> getVoterByEpicNo(@PathVariable("category") String category,@PathVariable("epicNo") String epicNo, Model model)
//    {
//        ApiResponse apiResponse = this.voterService.findVoterByEpicNoWhenCategory(epicNo,category);
//        return apiResponse;
//    }

}
