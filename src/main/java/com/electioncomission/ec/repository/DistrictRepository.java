package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.entity.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DistrictRepository extends CrudRepository<District, Integer> {

    public District findDistrictByDistrictId(int districId);

    @Transactional
    public void deleteDistrictByDistrictId(int districId);

}
