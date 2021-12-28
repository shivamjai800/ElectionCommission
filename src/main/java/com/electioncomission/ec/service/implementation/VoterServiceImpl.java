package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiErrorCode;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.Enums;
import com.electioncomission.ec.model.ReportFilter;
import com.electioncomission.ec.model.VisitSearch;
import com.electioncomission.ec.model.VotersUpdate;
import com.electioncomission.ec.repository.VoterRepository;
import com.electioncomission.ec.service.UsersService;
import com.electioncomission.ec.service.VisitService;
import com.electioncomission.ec.service.VoterService;
import com.electioncomission.ec.specifications.VisitSpecifications;
import com.electioncomission.ec.specifications.VoterSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.electioncomission.ec.specifications.VoterSpecifications.dashboardFilter;
import static com.electioncomission.ec.specifications.VoterSpecifications.dashboardFilterForEligibleVoter;

@Service
@Slf4j
public class VoterServiceImpl implements VoterService {
    @Autowired
    VoterRepository voterRepository;

    @Override
    public Voter addVoter(Voter voter) {
        return this.voterRepository.save(voter);
    }

    @Autowired
    VisitService visitService;

    @Autowired
    UsersService usersService;


    @Override
    public Voter updateVoterByEpicNo(Voter voter,String epicNo) {
        Voter oldVoterData = this.voterRepository.findVoterByEpicNo(epicNo);
        if(oldVoterData==null)
        {
            return null;
        }
        else
        {
            return this.voterRepository.save(voter);
        }
    }

    @Override
    public Voter findVoterByEpicNo(String epicNo) {
        return this.voterRepository.findVoterByEpicNo(epicNo);
    }

    @Override
    public void deleteVoterByEpicNo(String epicNo) {
        this.voterRepository.deleteVoterByEpicNo(epicNo);
    }

    @Override
    public void voteCastByEpicNo(String epicNo) {
        Voter voter = this.findVoterByEpicNo(epicNo);
        voter.setIsVoteCasted(true);
        this.updateVoterByEpicNo(voter, epicNo);
    }


    @Override
    public ApiResponse<Voter> findVoterByEpicNoWhenCategory(Principal principal,String epicNo, String category) {
        Voter voter = this.findVoterByEpicNo(epicNo);
        ApiResponse<Voter> voterApiResponse = new ApiResponse<Voter>();
        String userId = principal.getName();
        Users users = this.usersService.findUsersByUserId(Integer.parseInt(userId));


        if(voter == null || voter.getCategory()==null || !voter.getCategory().equals(category))
        {
            ApiError apiError = new ApiError("Either voter epic no is not correct or category", ApiErrorCode.VOTER_NOT_FOUND);
            voterApiResponse.setApiError(apiError);
            voterApiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        }
        else
        {
            boolean condition = (users.getUserRole().equals(Enums.UsersRole.BLO.getValue()) && (voter.getPartId() != users.getPartId()));
            condition  = condition || (users.getUserRole().equals(Enums.UsersRole.RO.getValue()) && (voter.getConstituencyId() != users.getConstituencyId()));
            condition  = condition || (users.getUserRole().equals(Enums.UsersRole.DEO.getValue()) && (voter.getDistrictId() != users.getDistrictId()));

            if(condition)
            {
                ApiError apiError = new ApiError("Voter is out of your area check epic Number", ApiErrorCode.VISIT_OUT_OF_LOGGED_USER_AREA);
                voterApiResponse.setApiError(apiError);
                voterApiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
            }
            else {
                voterApiResponse.setData(voter);
                voterApiResponse.setHttpStatus(HttpStatus.OK);
            }
        }
        return voterApiResponse;
    }

    // voter search by criteria : district, constituency, part and category.
    @Override
    public ApiResponse<List<Voter>> getVotersByEligiblityCriteria(Principal principal, VisitSearch visitSearch) {

        ApiResponse<List<Voter>> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else
        {
            List<Voter> voters = this.voterRepository.findAll(dashboardFilterForEligibleVoter(visitSearch,true));
            apiResponse.setData(voters);
            apiResponse.setHttpStatus(HttpStatus.OK);
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<String> updateVotersEligiblityOrCategory(Principal principal, VotersUpdate votersList) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else {

            Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
            if(!users.getUserRole().equals(Enums.UsersRole.RO.getValue()))
            {
                apiResponse.setHttpStatus(HttpStatus.FORBIDDEN);
                apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_PERMITTED));
            }
            else
            {
                if(votersList.getEligible() != null) {
                    votersList.getEligible().forEach(epicNo -> {
                        Voter voter = this.findVoterByEpicNo(epicNo);
                        if (voter != null) {
                            voter.setEligible(true);
//                            System.out.println(this.voterRepository.save(voter));
                            this.voterRepository.save(voter);
                        }
                    });
                }
                if(votersList.getInEligible() != null) {
                    votersList.getInEligible().forEach(epicNo -> {
                        Voter voter = this.findVoterByEpicNo(epicNo);
//                        System.out.println(voter);
                        if (voter != null) {
                            voter.setEligible(false);
//                            System.out.println(this.voterRepository.save(voter));
                            this.voterRepository.save(voter);
                        }
                    });
                }
                if(votersList.getAvco() != null) {
                    votersList.getAvco().forEach(epicNo -> {
                        Voter voter = this.findVoterByEpicNo(epicNo);
//                        System.out.println(voter);
                        if (voter != null) {
                            voter.setCategory(Enums.VoterCategory.AVCO.getValue());
//                            System.out.println(this.voterRepository.save(voter));
                            this.voterRepository.save(voter);
                        }
                    });
                }
                if(votersList.getNullCategory() != null) {
                    votersList.getNullCategory().forEach(epicNo -> {
                        Voter voter = this.findVoterByEpicNo(epicNo);
                        if (voter != null) {
                            voter.setCategory(null);
//                            System.out.println(this.voterRepository.save(voter));
                            this.voterRepository.save(voter);
                        }
                    });
                }
                apiResponse.setHttpStatus(HttpStatus.OK);
                apiResponse.setData("Voters data saved succesfully");
            }


        }
        return apiResponse;
    }

    @Override
    public ApiResponse<Voter> getNullCategoryOrAvcoVoterByEpicNo(Principal principal, String epicNo) {
        ApiResponse<Voter> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else {

            Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
            if(!users.getUserRole().equals(Enums.UsersRole.RO.getValue()))
            {
                apiResponse.setHttpStatus(HttpStatus.FORBIDDEN);
                apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_PERMITTED));
            }
            else if(epicNo.equals("") || epicNo==null)
            {
                apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
                apiResponse.setApiError(new ApiError(ApiErrorCode.EPICNO_IS_INVALID));
            }
            else
            {
                apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
                Voter voter = this.findVoterByEpicNo(epicNo);
                if(voter==null)
                {
                    apiResponse.setApiError(new ApiError(ApiErrorCode.VOTER_NOT_FOUND));
                }
                else if(voter.getCategory()!=null && !voter.getCategory().equals(Enums.VoterCategory.AVCO.getValue()) ) {
                    apiResponse.setApiError(new ApiError(ApiErrorCode.VOTER_NOT_FOUND));
                }
                else
                {
                    apiResponse.setHttpStatus(HttpStatus.OK);
                    apiResponse.setData(voter);
                }
            }
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<List<Voter>> getVotersByReportsFilter(Principal principal, ReportFilter reportFilter) {
        ApiResponse<List<Voter>> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else {
            Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
            if(false)
            {
                apiResponse.setHttpStatus(HttpStatus.FORBIDDEN);
                apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_PERMITTED));
            }
            else
            {
                List<Voter> voterList = new ArrayList<>();
                ApiResponse<List<Visit>> visitList = this.visitService.getVisitsBySpecification(VisitSpecifications.reportFilter(reportFilter));

                if(visitList.getHttpStatus().equals(HttpStatus.OK))
                {
                    if(visitList.getData()!=null) {
                        visitList.getData().forEach(e -> {
                            voterList.add(e.getVoter());
//                            System.out.println(e.getVoter());
                        });
                    }
                    apiResponse.setData(voterList);
                    apiResponse.setHttpStatus(HttpStatus.OK);
                }
                else
                {
                    apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
                }
            }

        }
        return apiResponse;


    }

    @Override
    public ApiResponse<List<Voter>> getVotersByBloPartId(Principal principal, Integer partId) {
        ApiResponse<List<Voter>> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else {
            Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
            if (!users.getUserRole().equals(Enums.UsersRole.BLO.getValue()) || users.getPartId() != partId) {
                apiResponse.setHttpStatus(HttpStatus.FORBIDDEN);
                apiResponse.setApiError(new ApiError(ApiErrorCode.VOTER_OUT_OF_BLO_PART));
            } else {
                VisitSearch visitSearch = new VisitSearch();
                visitSearch.setPartId(partId);
                List<Voter> voters = this.voterRepository.findAll(VoterSpecifications.dashboardFilter(visitSearch));
                apiResponse.setData(voters);
                apiResponse.setHttpStatus(HttpStatus.OK);
            }
        }
        return apiResponse;

    }

    @Override
    public ApiResponse<Voter> getVoterForBloByEpicNo(Principal principal, String epicNo) {

        ApiResponse<Voter> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else {
            Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
            if (!users.getUserRole().equals(Enums.UsersRole.BLO.getValue())) {
                apiResponse.setHttpStatus(HttpStatus.FORBIDDEN);
                apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_PERMITTED));
            } else {
               Voter voter = this.findVoterByEpicNo(epicNo);
               if(voter==null)
               {
                   apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                   apiResponse.setApiError(new ApiError(ApiErrorCode.VOTER_NOT_FOUND));
               }
               else if(voter.getPartId() != users.getPartId())
               {
                   apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                   apiResponse.setApiError(new ApiError(ApiErrorCode.VOTER_OUT_OF_BLO_PART));
               }
               else
               {
                   apiResponse.setHttpStatus(HttpStatus.OK);
                   apiResponse.setData(voter);
               }
            }
        }
        return apiResponse;
    }


}
