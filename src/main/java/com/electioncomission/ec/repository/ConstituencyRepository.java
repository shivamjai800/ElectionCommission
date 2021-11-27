package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ConstituencyRepository extends CrudRepository<Constituency, Integer> {

    public Constituency findConstituencyByConstituencyId(int constituencyId);

    @Transactional
    public void deleteConstituencyByConstituencyId(int constituencyId);
}
