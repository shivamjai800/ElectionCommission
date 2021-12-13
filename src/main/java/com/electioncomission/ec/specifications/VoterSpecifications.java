package com.electioncomission.ec.specifications;

import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.VisitSearch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VoterSpecifications {


    public static Specification<Voter> dashboardFilterCriteria(VisitSearch visitSearch) {
        return (voterRoot, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();
            if (visitSearch != null && visitSearch.getDistrictId() != null && visitSearch.getDistrictId() != 0) {
                predicateList.add(criteriaBuilder.equal(voterRoot.get("districtId"), visitSearch.getDistrictId()));
            }
            if (visitSearch != null && visitSearch.getConstituencyId() != null && visitSearch.getConstituencyId() != 0) {
                predicateList.add(criteriaBuilder.equal(voterRoot.get("constituencyId"), visitSearch.getConstituencyId()));
            }
            if (visitSearch != null && visitSearch.getPartId() != null && visitSearch.getPartId() != 0) {
                predicateList.add(criteriaBuilder.equal(voterRoot.get("partId"), visitSearch.getPartId()));
            }
            if (visitSearch != null && visitSearch.getCategory() != null && !visitSearch.getCategory().equals("")) {
                predicateList.add(criteriaBuilder.equal(voterRoot.get("category"), visitSearch.getCategory()));
            }
//            predicateList.add(criteriaBuilder.equal(voterRoot.get("visit").get("voterEpicNo"),voterRoot.get("epicNo")));


            Predicate reportFilterConditions = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
//                        Root<Visit> visitRoot = criteriaQuery.from(Visit.class);

//            criteriaQuery.multiselect(voterRoot.getModel().getAttributes().toArray());
//            System.out.println(voterRoot.getJoins());
//            voterRoot.getJoins().
//            Join<Voter,Visit> join = voterRoot.join("visit",JoinType.LEFT);
//            return join.on(reportFilterConditions).getOn();

//            join.on();
//            return criteriaBuilder.and(reportFilterConditions,voterRoot.join("visit",JoinType.LEFT).on(criteriaBuilder.equal(voterRoot.get("epicNo"),visitRoot.get("voterEpicNo"))).getOn());
//            return criteriaBuilder.and(reportFilterConditions,join.getOn());
                        return reportFilterConditions;

        };


    }

    public static Specification<Voter> dashboardFilter(VisitSearch visitSearch) {
        return Specification.where(dashboardFilterCriteria(visitSearch));

//
    }



}
