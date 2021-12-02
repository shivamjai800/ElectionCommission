package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.entity.Part;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PartRepository extends CrudRepository<Part, Integer> {

    public Part findPartByPartId(int partId);

    @Transactional
    public void deletePartByPartId(int partId);

    @Query("SELECT p FROM Part p WHERE p.constituencyId = :constituencyId")
    public List<Part> findAllPartNameByConstituencyId(int constituencyId);

//    @Query("SELECT p FROM Part p WHERE p.constituencyName = :constituencyName")
//    public List<Part> findAllPartNameByConstituencyName(String constituencyName);
}
