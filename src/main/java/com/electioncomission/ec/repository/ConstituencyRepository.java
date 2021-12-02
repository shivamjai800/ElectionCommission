package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.entity.Part;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ConstituencyRepository extends CrudRepository<Constituency, Integer> {

    public Constituency findConstituencyByConstituencyId(int constituencyId);

    @Transactional
    public void deleteConstituencyByConstituencyId(int constituencyId);

    @Query("SELECT c FROM Constituency c WHERE c.districtId = :districtId")
    public List<Constituency> findAllConstituencyNameByDistrictId(int districtId);
}
