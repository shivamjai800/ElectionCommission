package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.entity.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Integer> {
}
