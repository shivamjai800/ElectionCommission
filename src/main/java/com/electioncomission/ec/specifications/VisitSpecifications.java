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

            if(reportFilter.getDistrictId()!=null && reportFilter.getDistrictId()!=0)
            {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voter").get("districtId"),reportFilter.getDistrictId()));
            }
            if(reportFilter.getConstituencyId()!=null && reportFilter.getConstituencyId()!=0)
            {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voter").get("constituencyId"),reportFilter.getConstituencyId()));
            }
            if(reportFilter.getPartId()!=null && reportFilter.getPartId()!=0)
            {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voter").get("partId"),reportFilter.getPartId()));
            }
            if(reportFilter.getVoterCategory()!=null && !reportFilter.getVoterCategory().equals("all"))
            {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voterCategory"),reportFilter.getVoterCategory()));
            }
            if(reportFilter.getVoterEligiblity()!=null && !reportFilter.getVoterEligiblity().equals("all"))
            {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voterEligiblity"),reportFilter.getVoterEligiblity().equals("yes")?true:false));
            }
            if(reportFilter.getPhysicallyMet()!=null && !reportFilter.getPhysicallyMet().equals("all"))
            {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("physicallyMet"),reportFilter.getDistrictId().equals("yes")?true:false));
            }
            if(reportFilter.getForm_12dDelivered()!=null && !reportFilter.getForm_12dDelivered().equals("all"))
            {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("form_12dDelivered"),reportFilter.getForm_12dDelivered().equals("yes")?true:false));
            }
            if(reportFilter.getFilledForm_12dReceived()!=null && !reportFilter.getFilledForm_12dReceived().equals("all"))
            {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("filledForm_12dReceived"),reportFilter.getFilledForm_12dReceived().equals("yes")?true:false));
            }
            if(reportFilter.getVoteCasted()!=null && !reportFilter.getVoteCasted().equals("all"))
            {
                predicateList.add(criteriaBuilder.equal(visitRoot.get("voteCasted"),reportFilter.getVoteCasted().equals("yes")?true:false));
            }
            Predicate reportFilterConditions = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            return reportFilterConditions;
        };


    }


    public static Specification<Visit> reportFilter(ReportFilter reportFilter)
    {
        return Specification.where(reportsFilterCriteria(reportFilter));

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
