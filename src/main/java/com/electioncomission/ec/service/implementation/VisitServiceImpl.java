package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.SuccessMessage;
import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.model.VisitSearch;
import com.electioncomission.ec.model.Enums;
import com.electioncomission.ec.service.UsersService;
import com.electioncomission.ec.common.ApiErrorCode;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.model.VisitSearch;
import com.electioncomission.ec.repository.VoterRepository;
import com.electioncomission.ec.service.UsersService;
import com.electioncomission.ec.specifications.VisitSpecifications;
import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Visit;
import com.electioncomission.ec.model.ReportFilter;
import com.electioncomission.ec.repository.VisitRepository;
import com.electioncomission.ec.service.VisitService;
import com.electioncomission.ec.specifications.VoterSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.electioncomission.ec.common.ApiErrorCode.*;

@Service
@Slf4j
public class VisitServiceImpl implements VisitService {
    @Autowired
    VisitRepository visitRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    VoterRepository voterRepository;


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



    public void addImage(MultipartFile multipartFile, String absolutePath, String subFolderName, String fileName, Visit visit) {

        if(multipartFile==null)
        {
            log.debug("Image file for the "+fileName+" is null");
            return;
        }
        if (multipartFile.getSize() > 1) {
            if(fileName.startsWith("category"))
                visit.setCertificateImageId(fileName);
            if(fileName.startsWith("form12d"))
                visit.setForm_12dImageId(fileName);
            if(fileName.startsWith("selfie"))
                visit.setSelfieWithVoterImageId(fileName);
            if(fileName.startsWith("voter"))
                visit.setVoterIdImageId(fileName);
            File categoryImage;
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path uploadPath = Paths.get(absolutePath+subFolderName);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = null;
                if(fileName.startsWith("category"))
                    filePath = uploadPath.resolve(visit.getCertificateImageId() + ".jpeg");
                if(fileName.startsWith("form12d"))
                    filePath = uploadPath.resolve(visit.getForm_12dImageId() + ".jpeg");
                if(fileName.startsWith("selfie"))
                    filePath = uploadPath.resolve(visit.getSelfieWithVoterImageId() + ".jpeg");
                if(fileName.startsWith("voter"))
                    filePath = uploadPath.resolve(visit.getVoterIdImageId() + ".jpeg");
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                log.info("Successfully added image for epic Number  "+visit.getVoterEpicNo()+ "image name = "+fileName);
            } catch (IOException e) {
                log.error("Encountered Exception on Image save" + e.getMessage());
            }
        }
    }

    public Visit setAllBooleanNullToFalse(Visit visit)
    {
        if(visit.getIsPhysicallyMet()==null)
            visit.setIsPhysicallyMet(false);
        if(visit.getFirstVisit()==null)
            visit.setFirstVisit(false);
        if(visit.getIsVoterExpired()==null)
            visit.setIsVoterExpired(false);
        if(visit.getSecondVisit()==null)
            visit.setSecondVisit(false);
        if(visit.getForm_12dDelivered()==null)
            visit.setForm_12dDelivered(false);
        if(visit.getForm_12dDelivered()==null)
            visit.setForm_12dDelivered(false);
        if(visit.getFilledForm_12dReceived()==null)
            visit.setFilledForm_12dReceived(false);
        if(visit.getIsOptingForPostalBallot()==null)
            visit.setIsOptingForPostalBallot(false);

        return visit;

    }

    @Override
    public ApiResponse<String> addVoterVisit(Visit visit, String epicNo, MultipartFile certificateImage, MultipartFile form_12dImage, MultipartFile selfieWithVoterImage,
                                            MultipartFile voterIdImage) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Visit oldVisit = this.visitRepository.findVisitByVoterEpicNo(epicNo);
        if (oldVisit == null) {
            visit.setFirstVisit(true);
            visit.setSecondVisit(false);
            //time stamp code
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            visit.setFirstVisitTimestamp(ts);
            visit.setFirstVisit(true);
            addImage(certificateImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/","category","category_"+epicNo,visit);
            addImage(form_12dImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/","form12d","form12d_"+epicNo,visit);
            addImage(selfieWithVoterImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/","selfie","selfie_"+epicNo,visit);
            addImage(voterIdImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/","voter","voter_"+epicNo,visit);
            visit = setAllBooleanNullToFalse(visit);
            this.addVisit(visit);
            //Response code
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setData(SuccessMessage.FIRST_VISIT_CREATED_SUCCESSFULLY.getMessage());
        } else if (oldVisit.getIsVoterExpired()) {
            ApiError apiError = new ApiError("voter expired entered earlier", VOTER_EXPIRED);
            apiResponse.setApiError(apiError);
            apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
        } else if (oldVisit.getSecondVisit()) {
            ApiError apiError = new ApiError("all visits completed earlier", SECOND_VISIT_COMPLETED_EARLIER);
            apiResponse.setApiError(apiError);
            apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
        } else if (!oldVisit.getSecondVisit()) {
            oldVisit.setIsPhysicallyMet(visit.getIsPhysicallyMet());
            oldVisit.setSecondVisit(true);
            oldVisit.setSecondVisitRemarks(visit.getSecondVisitRemarks());
            oldVisit.setSecondVisitGpsCoordLat(visit.getFirstVisitGpsCoordLat());
            oldVisit.setSecondVisitGpsCoordLon(visit.getFirstVisitGpsCoordLon());

            oldVisit.setForm_12dDelivered(visit.getForm_12dDelivered());
            oldVisit.setForm_12dDeliveredRemarks(visit.getForm_12dDeliveredRemarks());

            oldVisit.setFilledForm_12dReceived(visit.getFilledForm_12dReceived());
            oldVisit.setFilledForm_12dReceivedRemarks(visit.getFilledForm_12dReceivedRemarks());

            //time stamp code
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            oldVisit.setSecondVisitTimestamp(ts);
            addImage(certificateImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/","category","category_"+epicNo,oldVisit);
            addImage(form_12dImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/","form12d","form12d_"+epicNo,oldVisit);
            addImage(selfieWithVoterImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/","selfie","selfie_"+epicNo,oldVisit);
            addImage(voterIdImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/","voter","voter_"+epicNo,oldVisit);

            this.visitRepository.save(oldVisit);
            // response code
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setData(SuccessMessage.SECOND_VISIT_CREATED_SUCCESSFULLY.getMessage());
        }
        return apiResponse;

    }

    @Override
    public Page<Visit> getVisitsByCriteria(ReportFilter reportFilter, int pageNo) {

//        System.out.println("report = "+reportFilter);
        Pageable pageable = PageRequest.of(pageNo, 50);
        Page<Visit> visits = this.visitRepository.findAll(VisitSpecifications.reportFilter(reportFilter), pageable);
        visits.forEach(e -> {
            System.out.println(e.toString());
        });
        return visits;
    }

    @Override
    public ApiResponse<List<Visit>> getVisitsByDashboardCriteria(Principal principal, VisitSearch visitSearch) {

        ApiResponse<List<Visit>> apiResponse = new ApiResponse<>();
        if (principal == null) {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        } else {
            Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
            VisitSearch visitSearch1 = new VisitSearch();
            visitSearch1.setConstituencyId(users.getConstituencyId());
            List<Visit> visits = this.visitRepository.findAll(VisitSpecifications.dashboardFilter(visitSearch1));
            apiResponse.setData(visits);
            apiResponse.setHttpStatus(HttpStatus.OK);
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<HashMap<String, Integer[]>> getVisitsCountByDashboardCriteria(Principal principal, VisitSearch visitSearch) {

        ApiResponse<HashMap<String, Integer[]>> apiResponse = new ApiResponse<>();
        if (principal == null) {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        } else {
            HashMap<String, Integer[]> graphData = new HashMap<String, Integer[]>();

            graphData.put("AVSC", getCountsFromVisitsAndVoters(visitSearch, "AVSC"));
            graphData.put("AVPD", getCountsFromVisitsAndVoters(visitSearch, "AVPD"));
            graphData.put("AVCO", getCountsFromVisitsAndVoters(visitSearch, "AVCO"));
            graphData.put("AVEW", getCountsFromVisitsAndVoters(visitSearch, "AVEW"));
            graphData.put("AVGE", getCountsFromVisitsAndVoters(visitSearch, "AVGE"));
            System.out.println(graphData);
            apiResponse.setData(graphData);
            apiResponse.setHttpStatus(HttpStatus.OK);
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<List<Visit>> getVisitsBySpecification(Specification<Visit> specification) {

        ApiResponse<List<Visit>> apiResponse = new ApiResponse<>();
        if (specification == null) {
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setApiError(new ApiError(SPECIFICATION_IS_NULL));
        } else {
            apiResponse.setHttpStatus(HttpStatus.OK);
            List<Visit> visits = this.visitRepository.findAll(specification);
//            visits.forEach(e-> System.out.println(e));
            apiResponse.setData(visits);
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<Visit> getVisitByEpicNoForBlo(Principal principal,String epicNo) {

        ApiResponse<Visit> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else {
            Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
            if (!users.getUserRole().equals(Enums.UsersRole.BLO.getValue())) {
                apiResponse.setHttpStatus(HttpStatus.FORBIDDEN);
                apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_PERMITTED));
            } else {
                Visit visit = this.visitRepository.findVisitByVoterEpicNo(epicNo);
                if (visit == null) {
                    apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                    apiResponse.setApiError(new ApiError(VISIT_NOT_FOUND));
                }
                else if(visit.getVoter().getPartId() != users.getPartId())
                {
                    apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                    apiResponse.setApiError(new ApiError(VOTER_OUT_OF_BLO_PART));
                }
                else {
                    apiResponse.setHttpStatus(HttpStatus.OK);
                    apiResponse.setData(visit);
                }
            }
        }
        return apiResponse;
    }

    private Integer[] getCountsFromVisitsAndVoters(VisitSearch visitSearch, String category) {
        int totalElectorCount = 0;
        int fieldVerifiedCount = 0;
        int form12dDeliveredCount = 0;
        int filledInForm12dReceivedCount = 0;

        List<Visit> visits = null;
        List<Voter> voters = null;

        visitSearch.setCategory(category);
        visits = this.visitRepository.findAll(VisitSpecifications.dashboardFilter(visitSearch));
        voters = this.voterRepository.findAll(VoterSpecifications.dashboardFilter(visitSearch));

        for (Visit visit : visits) {
            if (visit!=null && visit.getIsPhysicallyMet()!=null && visit.getIsPhysicallyMet() == true && (visit.getForm_12dDelivered()==null || visit.getForm_12dDelivered() == false) && (visit.getFilledForm_12dReceived()==null || visit.getFilledForm_12dReceived() == false)) {
                fieldVerifiedCount++;
            }else if (visit!=null && visit.getIsPhysicallyMet()!=null && visit.getIsPhysicallyMet() == true && (visit.getForm_12dDelivered()!=null && visit.getForm_12dDelivered() == true) && (visit.getFilledForm_12dReceived()==null || visit.getFilledForm_12dReceived() == false)) {
                form12dDeliveredCount++;
            }else if (visit!=null && visit.getIsPhysicallyMet()!=null && visit.getIsPhysicallyMet() == true && (visit.getForm_12dDelivered()!=null && visit.getForm_12dDelivered() == true) && (visit.getFilledForm_12dReceived()!=null && visit.getFilledForm_12dReceived() == true)) {
                filledInForm12dReceivedCount++;
            }
        }
        totalElectorCount = voters.size();
        Integer[] counts = new Integer[]{totalElectorCount, fieldVerifiedCount, form12dDeliveredCount, filledInForm12dReceivedCount};
        return counts;
    }

}
