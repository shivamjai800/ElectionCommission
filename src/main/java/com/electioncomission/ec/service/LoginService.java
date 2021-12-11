package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.model.JwtRequest;
import com.electioncomission.ec.model.JwtResponse;
import com.electioncomission.ec.model.OtpField;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;

public interface LoginService {
    public ApiResponse<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception;
    public void authenticate(String username, String password) throws Exception;

    public long getTimeDifferenceFromCurrentTime(Timestamp timestamp);

    public ApiResponse<String> generateAndSetOtp(OtpField otpField);
}
