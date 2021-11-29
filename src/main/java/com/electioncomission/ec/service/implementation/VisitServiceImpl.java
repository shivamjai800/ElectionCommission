package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.repository.VisitRepository;
import com.electioncomission.ec.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    public ApiResponse<Visit> addVoterVisit(Visit visit,int epicNo) {
        Visit oldVisit = this.visitRepository.findVisitByVoterEpicNo(epicNo);
        if(oldVisit==null)
        {
            visit.setFirstVisit(true);
            Date date = new Date();
            Timestamp ts=new Timestamp(date.getTime());
            visit.setFirstVisitTimestamp(ts);
            visit.setVoterExpired(false);

        }
        return null;
    }

}
