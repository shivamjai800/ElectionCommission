package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {
}
