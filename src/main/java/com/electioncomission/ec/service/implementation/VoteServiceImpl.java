package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Vote;
import com.electioncomission.ec.entity.Voter;
import com.electioncomission.ec.repository.VoteRepository;
import com.electioncomission.ec.service.VoteService;
import com.electioncomission.ec.service.VoterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Date;

import static com.electioncomission.ec.common.ApiErrorCode.VOTE_ALREADY_CASTED;

@Service
@Slf4j
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    VoterService voterService;

    @Override
    public Vote addVote(Vote vote) {
        return this.voteRepository.save(vote);
    }

    @Override
    public Vote updateVote(Vote vote, int voteId) {
        if (this.voteRepository.findVoteByVoteId(voteId) != null)
            return this.voteRepository.save(vote);
        return null;
    }

    @Override
    public Vote findVoteByVoteId(int voteId) {
        return this.voteRepository.findVoteByVoteId(voteId);
    }

    @Override
    public void deleteVoteByVoteId(int voteId) {
        this.voteRepository.deleteVoteByVoteId(voteId);
    }

    @Override
    public Vote findVoteByVoterEpicNo(String voterEpicNo) {
        return this.voteRepository.findVoteByVoterEpicNo(voterEpicNo);
    }

    @Override
    public ApiResponse<String> addVoterVote(Vote vote, String epicNo, MultipartFile envelopeImage, MultipartFile othersImage, MultipartFile selfieWithVoterImage,
                                            MultipartFile voterIdImage) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Vote oldVote = this.findVoteByVoterEpicNo(epicNo);
        if (oldVote == null) {
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            vote.setVoteCastTimestamp(ts);
            vote.setIsVoteCasted(true);
            Voter voter = this.voterService.findVoterByEpicNo(epicNo);
            voter.setIsVoteCasted(true);
            this.voterService.updateVoterByEpicNo(voter,epicNo);
            addImage(envelopeImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/vote/","envelope","envelope_"+epicNo,vote);
            addImage(othersImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/vote/","others","others_"+epicNo,vote);
            addImage(selfieWithVoterImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/vote/","selfie","selfie_"+epicNo,vote);
            addImage(voterIdImage,System.getProperty("user.dir") + "/src/main/webapp/static/images/vote/","voter","voter_"+epicNo,vote);
            this.addVote(vote);
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setData("Successfully added the vote");
//            voterService.voteCastByEpicNo(epicNo);
        } else {
            apiResponse.setApiError(new ApiError(VOTE_ALREADY_CASTED));
            apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
        }
        return apiResponse;
    }

    public void addImage(MultipartFile multipartFile, String absolutePath, String subFolderName, String fileName, Vote vote) {

        if(multipartFile==null)
        {
            log.debug("Image file for the "+fileName+" is null");
            return;
        }
        if (multipartFile.getSize() > 1) {
            if(fileName.startsWith("envelope"))
                vote.setEnvelopeImageId(fileName);
            if(fileName.startsWith("others"))
                vote.setOthersImageId(fileName);
            if(fileName.startsWith("selfie"))
                vote.setSelfieWithVoterImageId(fileName);
            if(fileName.startsWith("voter"))
                vote.setVoterIdImageId(fileName);
            File envelopeImage;
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path uploadPath = Paths.get(absolutePath+subFolderName);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = null;
                if(fileName.startsWith("envelope"))
                    filePath = uploadPath.resolve(vote.getEnvelopeImageId() + ".jpeg");
                if(fileName.startsWith("others"))
                    filePath = uploadPath.resolve(vote.getOthersImageId() + ".jpeg");
                if(fileName.startsWith("selfie"))
                    filePath = uploadPath.resolve(vote.getSelfieWithVoterImageId() + ".jpeg");
                if(fileName.startsWith("voter"))
                    filePath = uploadPath.resolve(vote.getVoterIdImageId() + ".jpeg");
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                log.info("Successfully added image for epic Number  "+ vote.getVoterEpicNo()+ "image name = "+fileName);
            } catch (IOException e) {
                log.error("Encountered Exception on Image save" + e.getMessage());
            }
        }
    }
}
