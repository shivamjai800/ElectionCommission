package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.VisitSearch;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface VoterService {
    public Voter addVoter(Voter voter);
    public Voter updateVoterByEpicNo(Voter voter, String epicNo);
    public Voter findVoterByEpicNo(String epicNo);
    public void deleteVoterByEpicNo(String epicNo);

    public ApiResponse<Voter> findVoterByEpicNoWhenCategory(String epicNo, String category);
    public ApiResponse<List<Voter>> getVotersByDashboardCriteria(Principal principal, VisitSearch visitSearch);
    public ApiResponse<String> updateVotersForEligiblity(Principal principal, Map<String, List<String>> voterList);
}
