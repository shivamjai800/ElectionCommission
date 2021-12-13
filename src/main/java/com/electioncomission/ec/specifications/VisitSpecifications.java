package com.electioncomission.ec.specifications;

import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.model.VisitSearch;
import com.electioncomission.ec.model.ReportFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.*;

public class VisitSpecifications {
    public static Specification<Visit> reportsFilterCriteria(ReportFilter reportFilter)
    {
        return (visitRoot ,  criteriaQuery , criteriaBuilder )-> {
            List<Predicate> predicateList = new ArrayList<>();

            predicateList.add(criteriaBuilder.equal(visitRoot.get("isPhysicallyMet"),reportFilter.getPhysicallyMet().equals("yes")));

            predicateList.add(criteriaBuilder.equal(visitRoot.get("form_12dDelivered"),reportFilter.getForm12dDelivered().equals("yes")));
            predicateList.add(criteriaBuilder.equal(visitRoot.get("filledForm_12dReceived"),reportFilter.getForm12dCollected().equals("yes")));
//                predicateList.add(criteriaBuilder.equal(visitRoot.get("form_12dCollected"),reportFilter.getForm12dCollected().equals("yes")));
            Predicate reportFilterConditions = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            return reportFilterConditions;
        };


    }
    public static Specification<Visit> reportFilterVoterType(ReportFilter reportFilter)
    {
        return (visitRoot ,  criteriaQuery , criteriaBuilder )-> {
            return criteriaBuilder.equal(visitRoot.get("voterCategory"),reportFilter.getVoterType());
        };
    };

    public static Specification<Visit> reportFilter(ReportFilter reportFilter)
    {
        return Specification.where(reportFilterVoterType(reportFilter)).and(reportsFilterCriteria(reportFilter));

    }
    public static Specification<Visit> dashboardFilterCriteria(VisitSearch visitSearch) {
        return (visitRoot, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (visitSearch != null && visitSearch.getDistrictId() != null && visitSearch.getDistrictId() != 0) {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voter").get("districtId"), visitSearch.getDistrictId()));
            }
            if (visitSearch != null && visitSearch.getConstituencyId() != null && visitSearch.getConstituencyId() != 0) {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voter").get("constituencyId"), visitSearch.getConstituencyId()));
            }
            if (visitSearch != null && visitSearch.getPartId() != null && visitSearch.getPartId() != 0) {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voter").get("partId"), visitSearch.getPartId()));
            }
            if (visitSearch != null && visitSearch.getCategory() != null && !visitSearch.getCategory().equals("")) {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voterCategory"), visitSearch.getCategory()));
            }

            Predicate reportFilterConditions = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            return reportFilterConditions;
        };
    }
    public static Specification<Visit> dashboardFilter(VisitSearch visitSearch) {
        return Specification.where(dashboardFilterCriteria(visitSearch));
    }
}
