package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.entity.Vote;
import com.electioncomission.ec.repository.VoteRepository;
import com.electioncomission.ec.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteRepository voteRepository;

    @Override
    public Vote addVote(Vote vote) {
        return this.voteRepository.save(vote);
    }

    @Override
    public Vote updateVote(Vote vote, int voteId) {
        if (this.voteRepository.findVoteByVoteId(voteId) != null)
            return this.voteRepository.save(vote);
        return null;
    }

    @Override
    public Vote findVoteByVoteId(int voteId) {
        return this.voteRepository.findVoteByVoteId(voteId);
    }

    @Override
    public void deleteVoteByVoteId(int voteId) {
        this.voteRepository.deleteVoteByVoteId(voteId);
    }
}
