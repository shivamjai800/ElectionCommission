package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.model.VisitSearch;
import com.electioncomission.ec.model.ReportFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface VisitService {
    public Visit addVisit(Visit visit);
    public Visit updateVisit(Visit visit, int visitId);
    public Visit findVisitByVisitId(int visitId);
    public void deleteVisitByVisitId(int visitId);

    public ApiResponse<Visit> addVoterVisit(Visit visit, String epicNo, MultipartFile certificateImage);

    public Page<Visit> getVisitsByCriteria(ReportFilter reportFilter, int pageNo);

    public ApiResponse<List<Visit>> getVisitsByDashboardCriteria(Principal principal, VisitSearch visitSearch);
    public ApiResponse<HashMap<String, Integer[]>> getVisitsCountByDashboardCriteria(Principal principal, VisitSearch visitSearch);

    public ApiResponse<List<Visit>> getVisitsBySpecification(Specification<Visit> specification);
}
