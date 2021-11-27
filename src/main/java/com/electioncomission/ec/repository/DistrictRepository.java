package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.entity.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends CrudRepository<District, Integer> {

    public District findDistrictByDistrictId(int districId);
    public void deleteDistrictByDistrictId(int districId);

}
