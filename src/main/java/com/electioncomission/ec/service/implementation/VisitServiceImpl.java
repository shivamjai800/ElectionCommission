package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.specifications.VisitSpecifications;
import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.model.ReportFilter;
import com.electioncomission.ec.repository.VisitRepository;
import com.electioncomission.ec.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static com.electioncomission.ec.common.ApiErrorCode.SECOND_VISIT_COMPLETED_EARLIER;
import static com.electioncomission.ec.common.ApiErrorCode.VOTER_EXPIRED;

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
    public ApiResponse<Visit> addVoterVisit(Visit visit, String epicNo) {
        ApiResponse<Visit> apiResponse = new ApiResponse<>();
        Visit oldVisit = this.visitRepository.findVisitByVoterEpicNo(epicNo);
        if (oldVisit == null) {
            visit.setFirstVisit(true);
            visit.setSecondVisit(false);
            //time stamp code
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            visit.setFirstVisitTimestamp(ts);
            this.addVisit(visit);

            //Response code
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setData(visit);
        } else if (oldVisit.isVoterExpired()) {
            ApiError apiError = new ApiError("voter expired entered earlier", VOTER_EXPIRED);
            apiResponse.setApiError(apiError);
            apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
        } else if (oldVisit.isSecondVisit()) {
            ApiError apiError = new ApiError("all visits completed earlier", SECOND_VISIT_COMPLETED_EARLIER);
            apiResponse.setApiError(apiError);
            apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
        } else if (!oldVisit.isSecondVisit()) {
            oldVisit.setPhysicallyMet(visit.isPhysicallyMet());
            oldVisit.setSecondVisit(true);
            oldVisit.setSecondVisitRemarks(visit.getSecondVisitRemarks());
            oldVisit.setSecondVisitGpsCoordLat(visit.getFirstVisitGpsCoordLat());
            oldVisit.setSecondVisitGpsCoordLon(visit.getFirstVisitGpsCoordLon());

            oldVisit.setForm_12dDelivered(visit.isForm_12dDelivered());
            oldVisit.setForm_12dDeliveredRemarks(visit.getForm_12dDeliveredRemarks());

            oldVisit.setFilledForm_12dReceived(visit.isFilledForm_12dReceived());
            oldVisit.setFilledForm_12dReceivedRemarks(visit.getFilledForm_12dReceivedRemarks());

            //time stamp code
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            oldVisit.setSecondVisitTimestamp(ts);

            this.visitRepository.save(oldVisit);
            // response code
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setData(visit);
        }
        return apiResponse;

    }

    @Override
    public Page<Visit> getVisitsByCriteria(ReportFilter reportFilter, int pageNo) {

//        System.out.println("report = "+reportFilter);
        Pageable pageable = PageRequest.of(pageNo, 50);
        Page<Visit> visits = this.visitRepository.findAll(VisitSpecifications.reportFilter(reportFilter),pageable);
        visits.forEach(e->{
            System.out.println(e.toString());
        });
        return visits;
    }

}
