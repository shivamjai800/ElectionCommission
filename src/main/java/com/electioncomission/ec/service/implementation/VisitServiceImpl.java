package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.repository.VisitRepository;
import com.electioncomission.ec.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    VisitRepository visitRepository;

    @Override
    public Visit addVisit(Visit visit) {
        return this.visitRepository.save(visit);
    }

    @Override
    public Visit updateVisit(Visit visit, int visitId) {
        if (this.visitRepository.findVisitByVisitId(visitId) != null)
            return this.visitRepository.save(visit);
        return null;
    }

    @Override
    public Visit findVisitByVisitId(int visitId) {
        return this.visitRepository.findVisitByVisitId(visitId);
    }

    @Override
    public void deleteVisitByVisitId(int visitId) {
        this.visitRepository.deleteVisitByVisitId(visitId);
    }
}
