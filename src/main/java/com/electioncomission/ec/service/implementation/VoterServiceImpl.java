package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiErrorCode;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.repository.VoterRepository;
import com.electioncomission.ec.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;

@Service
public class VoterServiceImpl implements VoterService {
    @Autowired
    VoterRepository voterRepository;

    @Override
    public Voter addVoter(Voter voter) {
        return this.voterRepository.save(voter);
    }

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
}
