package com.electioncomission.ec.service.implementation;


import com.electioncomission.ec.service.MiscellaneousService;
import com.electioncomission.ec.service.VisitService;
import com.electioncomission.ec.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class MiscellaneousServiceImpl implements MiscellaneousService {

    @Autowired
    VoterService voterService;

    @Autowired
    VisitService visitService;


}
