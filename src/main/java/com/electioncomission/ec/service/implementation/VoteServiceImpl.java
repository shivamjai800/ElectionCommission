package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.entity.Vote;
import com.electioncomission.ec.repository.VoteRepository;
import com.electioncomission.ec.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

import static com.electioncomission.ec.common.ApiErrorCode.SECOND_VISIT_COMPLETED_EARLIER;
import static com.electioncomission.ec.common.ApiErrorCode.VOTE_ALREADY_CASTED;

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

    @Override
    public Vote findVoteByVoterEpicNo(String voterEpicNo) {
        return this.voteRepository.findVoteByVoterEpicNo(voterEpicNo);
    }

    @Override
    public ApiResponse<Vote> addVoterVote(Vote vote, String epicNo) {
        ApiResponse<Vote> apiResponse = new ApiResponse<>();
        Vote oldVote = this.findVoteByVoterEpicNo(epicNo);
        if (oldVote == null) {
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            vote.setVoteCastTimestamp(ts);
            this.addVote(vote);
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setData(vote);
        } else {
            apiResponse.setApiError(new ApiError(VOTE_ALREADY_CASTED));
            apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
        }
        return apiResponse;
    }
}
