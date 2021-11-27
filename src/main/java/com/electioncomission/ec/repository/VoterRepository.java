package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.entity.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VoterRepository extends CrudRepository<Voter, Integer> {

    public Voter findVoterByEpicNo(String epicNo);

    @Transactional
    public void deleteVoterByEpicNo(String epicNo);
}
