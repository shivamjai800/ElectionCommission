package com.electioncomission.ec.repository;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.entity.Users;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    public Users findUsersByUserId(int userId);

    @Transactional
    public void deleteUsersByUserId(int userId);

    @Query("SELECT u FROM Users u WHERE u.mobileNumber = :mobileNumber")
    public Users findUsersByMobileNumber(String mobileNumber);

    public Users findUsersByUserName(String username);

    public List<Users> findAll(Specification<Users> spec);


}
