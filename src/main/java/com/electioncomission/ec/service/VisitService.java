package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.model.ReportFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VisitService {
    public Visit addVisit(Visit visit);
    public Visit updateVisit(Visit visit, int visitId);
    public Visit findVisitByVisitId(int visitId);
    public void deleteVisitByVisitId(int visitId);

    public ApiResponse<Visit> addVoterVisit(Visit visit, String epicNo);

    public Page<Visit> getVisitsByCriteria(ReportFilter reportFilter, int pageNo);
}
