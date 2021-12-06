package com.electioncomission.ec.specifications;

import com.electioncomission.ec.entity.Visit;
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

//            boolean temp = false;
//            if(reportFilter.getVoterEligiblity().equals("yes"))
//            {
//                predicateList.add(criteriaBuilder.equal(visitRoot.get("eligiblity"),reportFilter.getVoterEligiblity()));
//            }
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
}
