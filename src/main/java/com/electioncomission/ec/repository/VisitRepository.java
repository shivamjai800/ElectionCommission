package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Integer> {

    public Visit findVisitByVisitId(int visitId);

    public Visit findVisitByVoterEpicNo(String epicNo);

    @Transactional
    public void deleteVisitByVisitId(int visitId);

    public Page<Visit> findAll(Specification<Visit> spec, Pageable page);

//    public List<Object> findAll();
}
