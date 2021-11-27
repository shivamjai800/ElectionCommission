package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.entity.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends CrudRepository<Part, Integer> {

    public Part findPartByConstituencyId(int constituencyId);
}
