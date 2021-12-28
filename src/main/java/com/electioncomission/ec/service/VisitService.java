package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.model.VisitSearch;
import com.electioncomission.ec.model.ReportFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

public interface VisitService {

    public Visit addVisit(Visit visit);

    public Visit updateVisit(Visit visit, int visitId);

    public Visit findVisitByVisitId(int visitId);

    public void deleteVisitByVisitId(int visitId);

    public ApiResponse<String> addVoterVisit(Visit visit, String epicNo, MultipartFile certificateImage, MultipartFile form_12dImage, MultipartFile selfieWithVoterImage,
                                             MultipartFile voterIdImage, Principal principal);

    public Page<Visit> getVisitsByCriteria(ReportFilter reportFilter, int pageNo);

    public ApiResponse<List<Visit>> getVisitsByDashboardCriteria(Principal principal, VisitSearch visitSearch);

    public ApiResponse<HashMap<String, Integer[]>> getVisitsCountByDashboardCriteria(Principal principal, VisitSearch visitSearch);

    public ApiResponse<List<Visit>> getVisitsBySpecification(Specification<Visit> specification);

    public ApiResponse<Visit> getVisitByEpicNoForBlo(Principal principal, String epicNo);

    public Visit setAllBooleanNullToFalse(Visit visit);

    public void addImage(MultipartFile multipartFile, String absolutePath, String subFolderName, String fileName, Visit visit);

    public Integer[] getCountsFromVisitsAndVoters(VisitSearch visitSearch, String category);

    public boolean checkForAreaLock(Principal principal);


}
