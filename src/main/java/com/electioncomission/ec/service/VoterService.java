package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.ReportFilter;
import com.electioncomission.ec.model.VisitSearch;
import com.electioncomission.ec.model.VotersUpdate;

import java.security.Principal;
import java.util.List;

public interface VoterService {
    public Voter addVoter(Voter voter);
    public Voter updateVoterByEpicNo(Voter voter, String epicNo);
    public Voter findVoterByEpicNo(String epicNo);
    public void deleteVoterByEpicNo(String epicNo);
    public void voteCastByEpicNo(String epicNo);

    public ApiResponse<Voter> findVoterByEpicNoWhenCategory(String epicNo, String category);
    public ApiResponse<List<Voter>> getVotersByEligiblityCriteria(Principal principal, VisitSearch visitSearch);
    public ApiResponse<String> updateVotersEligiblityOrCategory(Principal principal, VotersUpdate votersList);
    public ApiResponse<Voter> getNullCategoryOrAvcoVoterByEpicNo(Principal principal, String epicNo);
    public ApiResponse<List<Voter>> getVotersByReportsFilter(Principal principal, ReportFilter reportFilter);
    public ApiResponse<List<Voter>> getVotersByBloPartId(Principal principal, Integer partId);
    public ApiResponse<Voter> getVoterForBloByEpicNo(Principal principal, String epicNo);

}
