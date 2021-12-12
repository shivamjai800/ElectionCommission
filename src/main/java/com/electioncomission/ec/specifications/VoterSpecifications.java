package com.electioncomission.ec.specifications;

import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.DashboardSearch;
import com.electioncomission.ec.model.ReportFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class VoterSpecifications {
    public static Specification<Voter> dashboardFilterCriteria(DashboardSearch dashboardSearch)
    {
        return (voterRoot ,  criteriaQuery , criteriaBuilder )-> {
            List<Predicate> predicateList = new ArrayList<>();
            if(dashboardSearch != null && dashboardSearch.getDistrictId() != null && dashboardSearch.getDistrictId() != 0) {
                predicateList.add(criteriaBuilder.equal(voterRoot.get("districtId"),dashboardSearch.getDistrictId()));
            }
            if(dashboardSearch != null && dashboardSearch.getConstituencyId() != null && dashboardSearch.getConstituencyId() != 0) {
                predicateList.add(criteriaBuilder.equal(voterRoot.get("constituencyId"),dashboardSearch.getConstituencyId()));
            }
            if(dashboardSearch != null && dashboardSearch.getPartId() != null && dashboardSearch.getPartId() != 0) {
                predicateList.add(criteriaBuilder.equal(voterRoot.get("partId"),dashboardSearch.getPartId()));
            }

            Predicate reportFilterConditions = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            return reportFilterConditions;
        };


    }

    public static Specification<Voter> dashboardFilter(DashboardSearch dashboardSearch)
    {
        return Specification.where(dashboardFilterCriteria(dashboardSearch));
    }
}
