package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;

public interface VisitService {
    public Visit addVisit(Visit visit);
    public Visit updateVisit(Visit visit, int visitId);
    public Visit findVisitByVisitId(int visitId);
    public void deleteVisitByVisitId(int visitId);

    public ApiResponse<Visit> addVoterVisit(Visit visit, String epicNo);
}
