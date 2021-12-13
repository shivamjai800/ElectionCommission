package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.VisitSearch;

import java.security.Principal;
import java.util.List;

public interface VoterService {
    public Voter addVoter(Voter voter);
    public Voter updateVoterByEpicNo(Voter voter, String epicNo);
    public Voter findVoterByEpicNo(String epicNo);
    public void deleteVoterByEpicNo(String epicNo);

    public ApiResponse<Voter> findVoterByEpicNoWhenCategory(String epicNo, String category);
    public ApiResponse<List<Voter>> getVotersByDashboardCriteria(Principal principal, VisitSearch visitSearch);
}
