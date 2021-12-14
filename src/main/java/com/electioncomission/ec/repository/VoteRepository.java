package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {

    public Vote findVoteByVoteId(int voteId);

    @Transactional
    public void deleteVoteByVoteId(int voteId);

    public Vote findVoteByVoterEpicNo(String voterEpicNo);
}
