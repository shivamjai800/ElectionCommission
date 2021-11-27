package com.electioncomission.ec.service;

import com.electioncomission.ec.entity.Voter;

public interface VoterService {
    public Voter addVoter(Voter voter);
    public Voter updateVoter(Voter voter, String epicNo);
    public Voter findVoterByEpicNo(String epicNo);
    public void deleteVoterByEpicNo(String epicNo);
}
