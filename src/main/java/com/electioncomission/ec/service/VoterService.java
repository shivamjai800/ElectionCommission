package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Voter;

public interface VoterService {
    public Voter addVoter(Voter voter);
    public Voter updateVoterByEpicNo(Voter voter, String epicNo);
    public Voter findVoterByEpicNo(String epicNo);
    public void deleteVoterByEpicNo(String epicNo);

    public ApiResponse<Voter> findVoterByEpicNoWhenCategory(String epicNo, String category);
}
