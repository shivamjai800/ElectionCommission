package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Integer> {

    public Visit findVisitByVisitId(int visitId);

    public Visit findVisitByVoterEpicNo(String epicNo);

    @Transactional
    public void deleteVisitByVisitId(int visitId);

//    public List<Object> findAll();
}
