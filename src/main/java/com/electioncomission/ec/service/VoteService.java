package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.entity.Vote;

public interface VoteService {
    public Vote addVote(Vote vote);
    public Vote updateVote(Vote vote, int voteId);
    public Vote findVoteByVoteId(int voteId);
    public void deleteVoteByVoteId(int voteId);
    public Vote findVoteByVoterEpicNo(String voterEpicNo);
    public ApiResponse<Vote> addVoterVote(Vote vote, String epicNo);
}
