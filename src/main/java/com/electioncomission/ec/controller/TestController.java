package com.electioncomission.ec.controller;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.*;
import com.electioncomission.ec.model.DashboardSearch;
import com.electioncomission.ec.model.JwtRequest;
import com.electioncomission.ec.model.JwtResponse;
import com.electioncomission.ec.model.OtpField;
import com.electioncomission.ec.model.*;
import com.electioncomission.ec.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class TestController {

    @Autowired
    DistrictService districtService;
    @Autowired
    ConstituencyService constituencyService;
    @Autowired
    PartService partService;
    @Autowired
    UsersService usersService;
    @Autowired
    VoterService voterService;
    @Autowired
    VoteService voteService;
    @Autowired
    VisitService visitService;
    @Autowired
    LoginService loginService;

//    District

    @PostMapping("/test/district")
    public District addDistrict(HttpServletRequest request, @RequestBody District district) {
        return this.districtService.addDistrict(district);
    }

    @GetMapping("/test/district/{districtId}")
    public District getDistrict(HttpServletRequest request, @PathVariable int districtId) {
        return this.districtService.findDistrictByDistrictId(districtId);
    }

    @PutMapping("/test/district/{districtId}")
    public District updateDistrict(HttpServletRequest request, @RequestBody District district, @PathVariable("districtId") int districtId) {
        return this.districtService.updateDistrict(district, districtId);
    }

    @DeleteMapping("/test/district/{districtId}")
    public void deleteDistrict(HttpServletRequest request, @PathVariable("districtId") int districtId) {
        this.districtService.deleteDistrictByDistrictId(districtId);
    }

//    Constituency

    @PostMapping("/test/constituency")
    public Constituency addConstituency(HttpServletRequest request, @RequestBody Constituency constituency) {
        return this.constituencyService.addConstituency(constituency);
    }

    @GetMapping("/test/constituency/{constituencyId}")
    public Constituency getConstituency(HttpServletRequest request, @PathVariable int constituencyId) {
        return this.constituencyService.findConstituencyByConstituencyId(constituencyId);
    }

    @PutMapping("/test/constituency/{constituencyId}")
    public Constituency updateConstituency(HttpServletRequest request, @RequestBody Constituency constituency, @PathVariable("constituencyId") int constituencyId) {
        return this.constituencyService.updateConstituency(constituency, constituencyId);
    }

    @DeleteMapping("/test/constituency/{constituencyId}")
    public void deleteConstituency(HttpServletRequest request, @PathVariable("constituencyId") int constituencyId) {
        this.constituencyService.deleteConstituencyByConstituencyId(constituencyId);
    }
    @PostMapping("/test/constituencies/{districtId}")
    public List<Constituency> findConstituenciesByDistrictId(HttpServletRequest request, @PathVariable("districtId") int districtId) {
        return this.constituencyService.findAllConstituencyByDistrictId(districtId);
    }

//    Part

    @PostMapping("/test/part")
    public Part addPart(HttpServletRequest request, @RequestBody Part part) {
        return this.partService.addPart(part);
    }

    @GetMapping("/test/part/{partId}")
    public Part getPart(HttpServletRequest request, @PathVariable int partId) {
        return this.partService.findPartByPartId(partId);
    }

    @PutMapping("/test/part/{partId}")
    public Part updatePart(HttpServletRequest request, @RequestBody Part part, @PathVariable("partId") int partId) {
        return this.partService.updatePart(part, partId);
    }

    @DeleteMapping("/test/part/{partId}")
    public void deletePart(HttpServletRequest request, @PathVariable("partId") int partId) {
        this.partService.deletePartByPartId(partId);
    }
    @PostMapping("/test/parts/{constituencyId}")
    public List<Part> findPartsByConstituencyId(HttpServletRequest request, @PathVariable("constituencyId") int constituencyId) {
        return this.partService.findPartsByConstituencyId(constituencyId);
    }

//    Users

    @PostMapping("/test/users")
    public Users addUsers(HttpServletRequest request, @RequestBody Users users) {
        return this.usersService.addUsers(users);
    }

    @GetMapping("/test/users/{userId}")
    public Users getUsers(HttpServletRequest request, @PathVariable int userId) {
        return this.usersService.findUsersByUserId(userId);
    }

    @PutMapping("/test/users/{userId}")
    public Users updateUsers(HttpServletRequest request, @RequestBody Users users, @PathVariable("userId") int userId) {
        return this.usersService.updateUsers(users, userId);
    }

    @DeleteMapping("/test/users/{userId}")
    public void deleteUsers(HttpServletRequest request, @PathVariable("userId") int userId) {
        this.usersService.deleteUsersByUserId(userId);
    }

//  Voter

    @PostMapping("/test/voter")
    public Voter addVoter(HttpServletRequest request, @RequestBody Voter voter) {
        return this.voterService.addVoter(voter);
    }

    @GetMapping("/test/voter/{epicNo}")
    public Voter getVoter(HttpServletRequest request, @PathVariable String epicNo) {
        return this.voterService.findVoterByEpicNo(epicNo);
    }

    @PutMapping("/test/voter/{epicNo}")
    public Voter updateVoter(HttpServletRequest request, @RequestBody Voter voter, @PathVariable("epicNo") String epicNo) {
        return this.voterService.updateVoterByEpicNo(voter, epicNo);
    }

    @DeleteMapping("/test/voter/{epicNo}")
    public void deleteVoter(HttpServletRequest request, @PathVariable("epicNo") String epicNo) {
        this.voterService.deleteVoterByEpicNo(epicNo);
    }
    @PostMapping("/test/voters")
    public List<Voter> getVoterByDashboardFilter(@RequestBody DashboardSearch dashboardSearch) {
        return this.voterService.getVotersByDashboardFilter(dashboardSearch);
    }

//  Vote

    @PostMapping("/test/vote")
    public Vote addVote(HttpServletRequest request, @RequestBody Vote vote) {
        return this.voteService.addVote(vote);
    }

    @GetMapping("/test/vote/{voteId}")
    public Vote getVote(HttpServletRequest request, @PathVariable int voteId) {
        return this.voteService.findVoteByVoteId(voteId);
    }

    @PutMapping("/test/vote/{voteId}")
    public Vote updateVote(HttpServletRequest request, @RequestBody Vote vote, @PathVariable("voteId") int voteId) {
        return this.voteService.updateVote(vote, voteId);
    }

    @DeleteMapping("/test/vote/{voteId}")
    public void deleteVote(HttpServletRequest request, @PathVariable("voteId") int voteId) {
        this.voteService.deleteVoteByVoteId(voteId);
    }

//  Visit

    @PostMapping("/test/visit")
    public Visit addVisit(HttpServletRequest request, @RequestBody Visit visit) {
        return this.visitService.addVisit(visit);
    }

    @GetMapping("/test/visit/{visitId}")
    public Visit getVisit(HttpServletRequest request, @PathVariable int visitId) {
        return this.visitService.findVisitByVisitId(visitId);
    }

    @PutMapping("/test/visit/{visitId}")
    public Visit updateVisit(HttpServletRequest request, @RequestBody Visit visit, @PathVariable("visitId") int visitId) {
        return this.visitService.updateVisit(visit, visitId);
    }

    @DeleteMapping("/test/visit/{visitId}")
    public void deleteVisit(HttpServletRequest request, @PathVariable("visitId") int visitId) {
        this.visitService.deleteVisitByVisitId(visitId);
    }

    //
    @GetMapping("/check")
    public ApiResponse<String> testRedirectOnSuccess() {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setHttpStatus(HttpStatus.OK);
        return apiResponse;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")

    public ResponseEntity<ApiResponse<JwtResponse>> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest, BindingResult result) throws Exception {

        System.out.println(authenticationRequest);
        if (result.hasErrors()) {
            ApiResponse<JwtResponse> apiResponse = new ApiResponse<>();
            apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            ApiError apiError = new ApiError();
            apiError.setSubMessage("Top errors is only shown, total errors in the request is " + result.getErrorCount());
            apiError.setMessage(result.getAllErrors().get(0).getDefaultMessage());
            apiResponse.setApiError(apiError);
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
        }
        ApiResponse<JwtResponse> apiResponse = loginService.createAuthenticationToken(authenticationRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PostMapping("/test")
    public List<Users> findBloByConstituencyIdAndKeyword(@RequestBody BloSearch bloSearch) {
//        return this.usersService.findBloByConstituencyIdAndKeyword(bloSearch.getConstituencyId(), bloSearch.getKeyword());
        return null;
    }

    @PostMapping("/otp")
    public ResponseEntity<ApiResponse<String>> generateOtp(HttpServletRequest request, @RequestBody @Valid OtpField otpField, BindingResult result, Model model) {
        ApiResponse<String> apiResponse = this.loginService.generateAndSetOtp(otpField);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping("/dashboard/blos/{keyword}")
    public ResponseEntity<ApiResponse<List<Users>>> getBlosByConstituencyId(Principal principal,@PathVariable("keyword") String keyword)
    {
        ApiResponse<List<Users>> apiResponse = this.usersService.findBloByConstituencyIdAndKeyword(principal,keyword);
        return new ResponseEntity<>(apiResponse,apiResponse.getHttpStatus());
    }

    @PutMapping("/dashboard/blo/{blo_id}")
    public ResponseEntity<ApiResponse<String>> updateBloMobileNumber(Principal principal, @PathVariable("blo_id") Integer bloId, @RequestBody @Valid BloUpdate bloUpdate, BindingResult bindingResult, Model model)
    {
//        System.out.println(bloUpdate.toString());
        if (bindingResult.hasErrors()) {
            ApiResponse<String> apiResponse = new ApiResponse<>();
            apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            ApiError apiError = new ApiError();
            apiError.setSubMessage("Top errors is only shown, total errors in the request is " + bindingResult.getErrorCount());
            apiError.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            apiResponse.setApiError(apiError);
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
        }

        ApiResponse<String> apiResponse = this.usersService.updateBloMobileNumber(principal,bloId,bloUpdate.getMobileNumber());
        return new ResponseEntity<>(apiResponse,apiResponse.getHttpStatus());
    }


}
