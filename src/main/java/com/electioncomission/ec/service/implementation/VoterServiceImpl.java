package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiErrorCode;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.Enums;
import com.electioncomission.ec.model.VisitSearch;
import com.electioncomission.ec.repository.VoterRepository;
import com.electioncomission.ec.service.UsersService;
import com.electioncomission.ec.service.VoterService;
import com.electioncomission.ec.specifications.VisitSpecifications;
import com.electioncomission.ec.specifications.VoterSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import javax.validation.constraints.Null;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import static com.electioncomission.ec.specifications.VoterSpecifications.dashboardFilter;

@Service
public class VoterServiceImpl implements VoterService {
    @Autowired
    VoterRepository voterRepository;

    @Override
    public Voter addVoter(Voter voter) {
        return this.voterRepository.save(voter);
    }

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
    public ApiResponse<Voter> findVoterByEpicNoWhenCategory(String epicNo, String category) {
        Voter voter = this.findVoterByEpicNo(epicNo);
        ApiResponse<Voter> voterApiResponse = new ApiResponse<Voter>();
        if(voter == null || voter.getCategory()==null || !voter.getCategory().equals(category))
        {
            ApiError apiError = new ApiError("Either voter epic no is not correct or category", ApiErrorCode.VOTER_NOT_FOUND);
            voterApiResponse.setApiError(apiError);
            voterApiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        }
        else
        {
            voterApiResponse.setData(voter);
            voterApiResponse.setHttpStatus(HttpStatus.OK);
        }
        return voterApiResponse;
    }

    @Override
    public ApiResponse<List<Voter>> getVotersByDashboardCriteria(Principal principal, VisitSearch visitSearch) {

        ApiResponse<List<Voter>> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else
        {
            List<Voter> voters = this.voterRepository.findAll(dashboardFilter(visitSearch));
            apiResponse.setData(voters);
            apiResponse.setHttpStatus(HttpStatus.OK);
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<String> updateVotersForEligiblity(Principal principal, Map<String, List<String>> voterList) {
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
                voterList.get("eligible").forEach(epicNo -> {
                    Voter voter = this.findVoterByEpicNo(epicNo);
                    if(voter!=null) {
                        voter.setEligible(true);
                        System.out.println(this.voterRepository.save(voter));
                    }
                });
                voterList.get("inEligible").forEach(epicNo -> {
                        Voter voter = this.findVoterByEpicNo(epicNo);
                        if(voter!=null) {
                            voter.setEligible(false);
                            System.out.println(this.voterRepository.save(voter));

                        }
                });
                apiResponse.setHttpStatus(HttpStatus.OK);
                apiResponse.setData("Users data saved succesfully");
            }


        }
        return apiResponse;
    }

}
