package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.repository.VoterRepository;
import com.electioncomission.ec.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoterServiceImpl implements VoterService {
    @Autowired
    VoterRepository voterRepository;

    @Override
    public Voter addVoter(Voter voter) {
        return this.voterRepository.save(voter);
    }

    @Override
    public Voter updateVoter(Voter voter, String epicNo) {
        if (this.voterRepository.findVoterByEpicNo(epicNo) != null)
            return this.voterRepository.save(voter);
        return null;
    }

    @Override
    public Voter findVoterByEpicNo(String epicNo) {
        return this.voterRepository.findVoterByEpicNo(epicNo);
    }

    @Override
    public void deleteVoterByEpicNo(String epicNo) {
        this.voterRepository.deleteVoterByEpicNo(epicNo);
    }
}
