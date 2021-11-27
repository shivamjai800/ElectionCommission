package com.electioncomission.ec.service;

import com.electioncomission.ec.entity.Visit;

public interface VisitService {
    public Visit addVisit(Visit visit);
    public Visit updateVisit(Visit visit, int visitId);
    public Visit findVisitByVisitId(int visitId);
    public void deleteVisitByVisitId(int visitId);
}
