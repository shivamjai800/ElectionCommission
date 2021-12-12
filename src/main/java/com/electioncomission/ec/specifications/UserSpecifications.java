package com.electioncomission.ec.specifications;

import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.model.ReportFilter;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class UserSpecifications {

    public static Specification<Users> selectRole(Integer constituencyId,String userRole) {
        return (usersRoot, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(usersRoot.get("constituencyId"),constituencyId));
            predicateList.add(criteriaBuilder.equal(usersRoot.get("userRole"), userRole));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
    public static Specification<Users> likeKeyword(String keyword)
    {
        return (usersRoot, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.like(usersRoot.get("firstName"),"%"+keyword+"%"));
            predicateList.add(criteriaBuilder.like(usersRoot.get("lastName"),"%"+keyword+"%"));
            predicateList.add(criteriaBuilder.like(usersRoot.get("userName"),"%"+keyword+"%"));
            predicateList.add(criteriaBuilder.like(usersRoot.get("userId").as(String.class),"%"+keyword+"%"));
            Predicate finalPredicate = criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
            return finalPredicate;

        };
    }

    public static Specification<Users> getUsersByUserRoleAndKeyword(Integer constituencyId, String keyword, String userRole)
    {
        return Specification.where(selectRole(constituencyId,userRole)).and(likeKeyword(keyword));
    }
}
