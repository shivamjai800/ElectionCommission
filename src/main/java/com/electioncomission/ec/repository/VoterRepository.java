package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.entity.Voter;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VoterRepository extends CrudRepository<Voter, String> {

    @Query("SELECT v FROM Voter v WHERE v.epicNo = :epicNo")
    public Voter findVoterByEpicNo(@Param("epicNo") String epicNo);

    @Transactional
    public void deleteVoterByEpicNo(String epicNo);

    public List<Voter> findAll(Specification<Voter> spec);
}
