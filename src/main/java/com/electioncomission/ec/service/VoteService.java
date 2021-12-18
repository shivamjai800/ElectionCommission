package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Vote;
import org.springframework.web.multipart.MultipartFile;

public interface VoteService {
    public Vote addVote(Vote vote);
    public Vote updateVote(Vote vote, int voteId);
    public Vote findVoteByVoteId(int voteId);
    public void deleteVoteByVoteId(int voteId);
    public Vote findVoteByVoterEpicNo(String voterEpicNo);
    public ApiResponse<String> addVoterVote(Vote vote, String epicNo, MultipartFile envelopeImage, MultipartFile othersImage, MultipartFile selfieWithVoterImage,
                                            MultipartFile voterIdImage);
}
