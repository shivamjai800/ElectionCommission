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


    public static Specification<Voter> dashboardFilterCriteria(VisitSearch visitSearch,String isEligible) {
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
            if(isEligible!=null && !isEligible.equals(""))
            {
                if(isEligible.equals("eligible"))
                {
                    predicateList.add(criteriaBuilder.equal(voterRoot.get("isEligible"),true));
                }
                if(isEligible.equals("inEligible"))
                {
                    predicateList.add(criteriaBuilder.equal(voterRoot.get("isEligible"),false));
                }

            }

            Predicate reportFilterConditions = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

                        return reportFilterConditions;

        };


    }

    public static Specification<Voter> dashboardFilter(VisitSearch visitSearch) {
        return Specification.where(dashboardFilterCriteria(visitSearch,null));

//
    }
    public static Specification<Voter> dashboardFilterForEligibleVoter(VisitSearch visitSearch,boolean isEligible)
    {
        return Specification.where(dashboardFilterCriteria(visitSearch,isEligible?"eligible":"inEligible"));
    }



}
