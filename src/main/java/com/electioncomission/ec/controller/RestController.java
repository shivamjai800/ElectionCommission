package com.electioncomission.ec.controller;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiErrorCode;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.*;
import com.electioncomission.ec.model.VisitSearch;
import com.electioncomission.ec.model.JwtRequest;
import com.electioncomission.ec.model.JwtResponse;
import com.electioncomission.ec.model.OtpField;
import com.electioncomission.ec.model.*;
import com.electioncomission.ec.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@Slf4j
public class RestController {

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
    @Autowired
    Validator validator;



    @PostMapping("/test/constituencies/{districtId}")
    public List<Constituency> findConstituenciesByDistrictId(HttpServletRequest request, @PathVariable("districtId") int districtId) {
        return this.constituencyService.findAllConstituencyByDistrictId(districtId);
    }

    @PostMapping("/test/parts/{constituencyId}")
    public List<Part> findPartsByConstituencyId(HttpServletRequest request, @PathVariable("constituencyId") int constituencyId) {
        return this.partService.findPartsByConstituencyId(constituencyId);
    }

    //  Open Visit Apis ends

    //test
    @GetMapping("/check")
    public ApiResponse<String> testRedirectOnSuccess() {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setHttpStatus(HttpStatus.OK);
        return apiResponse;
    }

    @PostMapping("/test")
    public List<Users> findBloByConstituencyIdAndKeyword(@RequestBody BloSearch bloSearch) {
//        return this.usersService.findBloByConstituencyIdAndKeyword(bloSearch.getConstituencyId(), bloSearch.getKeyword());
        return null;
    }


    //login otp starts
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

    @PostMapping("/otp")
    public ResponseEntity<ApiResponse<String>> generateOtp(HttpServletRequest request, @RequestBody @Valid OtpField otpField, BindingResult result, Model model) {
        ApiResponse<String> apiResponse = this.loginService.generateAndSetOtp(otpField);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    //login otp starts
    // dashboard starts
    @PostMapping("/dashboard/voters")
    public ResponseEntity<ApiResponse<List<Voter>>> getVotersByDashboardFilter(Principal principal, @RequestBody VisitSearch visitSearch) {
        ApiResponse<List<Voter>> apiResponse = this.voterService.getVotersByEligiblityCriteria(principal, visitSearch);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping("/dashboard/blos/{keyword}")
    public ResponseEntity<ApiResponse<List<Users>>> getBlosByConstituencyId(Principal principal, @PathVariable("keyword") String keyword) {
        ApiResponse<List<Users>> apiResponse = this.usersService.findBloByConstituencyIdAndKeyword(principal, keyword);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PutMapping("/dashboard/blo/{blo_id}")
    public ResponseEntity<ApiResponse<String>> updateBloMobileNumber(Principal principal, @PathVariable("blo_id") Integer bloId, @RequestBody @Valid BloUpdate bloUpdate, BindingResult bindingResult, Model model) {
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

        ApiResponse<String> apiResponse = this.usersService.updateBloMobileNumber(principal, bloId, bloUpdate.getMobileNumber());
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PostMapping("/dashboard/chart")
    public ResponseEntity<ApiResponse<HashMap<String, Integer[]>>> getDashboardData(Principal principal, @Valid @RequestBody VisitSearch visitSearch) {
        System.out.println(visitSearch);
        ApiResponse<HashMap<String, Integer[]>> apiResponse = this.visitService.getVisitsCountByDashboardCriteria(principal, visitSearch);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
    // dashboard ends

    // admin starts
    @PostMapping("/admin/voters")
    public ResponseEntity<ApiResponse<List<Visit>>> getEligibleVoter(Principal principal) {
        ApiResponse<List<Visit>> apiResponse = new ApiResponse<>();
        Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
        if (users.getUserRole().equals(Enums.UsersRole.RO.getValue())) {
            apiResponse = this.visitService.getVisitsByDashboardCriteria(principal, null);
        }
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping("/admin/voter/{epicNo}")
    public ResponseEntity<ApiResponse<Voter>> getNullCategoryOrAvcoVoterByEpicNo(Principal principal, @PathVariable("epicNo") String epicNo, Model model) {
        ApiResponse<Voter> apiResponse = new ApiResponse<>();
        Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
        apiResponse = this.voterService.getNullCategoryOrAvcoVoterByEpicNo(principal, epicNo);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PutMapping("/admin/voters")
    public ResponseEntity<ApiResponse<String>> updateVoterEligiblityOrCategory(Principal principal, @RequestBody VotersUpdate voterList) {
        ApiResponse<String> apiResponse = this.voterService.updateVotersEligiblityOrCategory(principal, voterList);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
    // admin ends

    // visit
    @PostMapping(value = "/visit", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<String>> addVisit(@ModelAttribute("visit") String visitString,
                                                        @RequestParam(value = "certificateImage", required = false) MultipartFile certificateImage,
                                                        @RequestParam(value = "form_12dImage", required = false) MultipartFile form_12dImage,
                                                        @RequestParam(value = "selfieWithVoterImage", required = false) MultipartFile selfieWithVoterImage,
                                                        @RequestParam(value = "voterIdImage", required = false) MultipartFile voterIdImage,
                                                        BindingResult bindingResult) {

        Visit visit =null;
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try{
            ObjectMapper mapper = new ObjectMapper();
             visit = mapper.readValue(visitString, Visit.class);
        }
        catch (JsonProcessingException exception)
        {
            log.error("Unable to convert json string to visit"+exception.getMessage());
            apiResponse.setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            apiResponse.setApiError(new ApiError(ApiErrorCode.JSON_STRING_PARSE_FAILED));
            return new ResponseEntity<>(apiResponse,apiResponse.getHttpStatus());
        }

        if (bindingResult.hasErrors()) {
            String errorMessage = "";
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (int i = 0; i < errorList.size(); i++) {
                errorMessage = errorMessage.concat(errorList.get(i).getDefaultMessage());
            }
            ApiError apiError = new ApiError(ApiErrorCode.REQUEST_BODY_SENT_HAS_ERRORS);
            apiError.setSubMessage(errorMessage);
            apiResponse.setApiError(apiError);
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        } else {
            System.out.println(visit);
            apiResponse = this.visitService.addVoterVisit(visit,
                    visit.getVoterEpicNo(),
                    certificateImage,
                    form_12dImage,
                    selfieWithVoterImage,
                    voterIdImage);

        }
        return new ResponseEntity<>(apiResponse,apiResponse.getHttpStatus());
    }

    @PostMapping("/vote")
    public ResponseEntity<ApiResponse<String>> addVote(@ModelAttribute("vote") String voteString,
                          @RequestParam(value = "envelopeImage", required = false) MultipartFile envelopeImage,
                          @RequestParam(value = "othersImage", required = false) MultipartFile othersImage,
                          @RequestParam(value = "selfieWithVoterImage", required = false) MultipartFile selfieWithVoterImage,
                          @RequestParam(value = "voterIdImage", required = false) MultipartFile voterIdImage, Principal principal) {

        ApiResponse<String> apiResponse = new ApiResponse<>();
        Vote vote = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            vote = mapper.readValue(voteString, Vote.class);
        }
        catch (JsonProcessingException exception)
        {
            log.error("Unable to convert json string to visit"+exception.getMessage());
            apiResponse.setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            apiResponse.setApiError(new ApiError(ApiErrorCode.JSON_STRING_PARSE_FAILED));
            return new ResponseEntity<>(apiResponse,apiResponse.getHttpStatus());
        }
        System.out.println(voteString);
        System.out.println(vote);
            String userId = principal.getName();
            System.out.println(vote);
            apiResponse = this.voteService.addVoterVote(vote,
                    vote.getVoterEpicNo(),
                    envelopeImage,
                    othersImage,
                    selfieWithVoterImage,
                    voterIdImage);
        return new ResponseEntity<>(apiResponse,apiResponse.getHttpStatus());
    }


    /* API's for Sumana and Shivom.

     */
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<Users>> getBloInformation(Principal principal)
    {
        ApiResponse apiResponse = this.usersService.getBloInformation(principal);
        return new ResponseEntity(apiResponse,apiResponse.getHttpStatus());
    }

    @GetMapping("/voter/{epicNo}")
    public ResponseEntity<ApiResponse<Voter>> getVoterByEpicNo(Principal principal, @PathVariable("epicNo") String epicNo) {
        ApiResponse<Voter> apiResponse = this.voterService.getVoterForBloByEpicNo(principal,epicNo);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping("/visit/{epicNo}")
    public ResponseEntity<ApiResponse<Visit>> getVisitByEpicNoForBlo(Principal principal, @PathVariable("epicNo") String epicNo) {
        ApiResponse<Visit> apiResponse = this.visitService.getVisitByEpicNoForBlo(principal,epicNo);
        return new ResponseEntity(apiResponse,apiResponse.getHttpStatus());
    }

    @GetMapping("/voters/{partId}")
    public ResponseEntity<ApiResponse<List<Voter>>> getVotersByBloPartId(Principal principal, @PathVariable("partId") Integer partId) {

        ApiResponse<List<Voter>> apiResponse = this.voterService.getVotersByBloPartId(principal,partId);
        return new ResponseEntity(apiResponse,apiResponse.getHttpStatus());
    }


    // Lock the data
    @PutMapping("/district/{districtId}")
    public ResponseEntity<ApiResponse<String>> updateDistrictByDistricId(Principal principal,@PathVariable("districtId") Integer districtId, @RequestBody AreaUpdate areaUpdate) {
        ApiResponse<String> apiResponse = this.districtService.updateDistrict(areaUpdate, districtId,principal);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


}

