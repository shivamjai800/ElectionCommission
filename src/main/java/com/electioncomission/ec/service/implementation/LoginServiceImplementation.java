package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiErrorCode;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.model.JwtRequest;
import com.electioncomission.ec.model.JwtResponse;
import com.electioncomission.ec.model.OtpField;
import com.electioncomission.ec.security.CustomUserDetails;
import com.electioncomission.ec.security.CustomUserDetailsServiceImpl;
import com.electioncomission.ec.security.JwtTokenUtil;
import com.electioncomission.ec.service.LoginService;
import com.electioncomission.ec.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class LoginServiceImplementation implements LoginService {

    @Autowired
    UsersService usersService;
    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ApiResponse<JwtResponse> createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
        UserDetails userDetails = null;
        ApiResponse<JwtResponse> apiResponse = new ApiResponse<>();
        ApiError apiError;
        if (authenticationRequest.getUsername() != null && !authenticationRequest.getUsername().equals("")) {
            Users users = this.usersService.findUsersByUserName(authenticationRequest.getUsername());
            try {
                authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            } catch (BadCredentialsException e) {
                apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
                apiResponse.setApiError(new ApiError(ApiErrorCode.INVALID_USER_CREDENTIALS));
                return apiResponse;
            }
            userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
            System.out.println(userDetails.toString());
        } else if (authenticationRequest.getMobileNumber() != null && !authenticationRequest.getMobileNumber().equals("")) {
            CustomUserDetails customUserDetails = userDetailsService.loadUserByMobileNumber(authenticationRequest.getMobileNumber());

            if (!customUserDetails.getOtp().equals(authenticationRequest.getOtp())) {
                apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
                apiResponse.setApiError(new ApiError(ApiErrorCode.INVALID_USER_CREDENTIALS));
                return apiResponse;
            }

            if (getTimeDifferenceFromCurrentTime(customUserDetails.getOtpGenerationTime()) > 300) {
                apiResponse.setHttpStatus(HttpStatus.GATEWAY_TIMEOUT);
                apiResponse.setApiError(new ApiError(ApiErrorCode.OTP_EXPIRED));
                return apiResponse;
            }
            userDetails = customUserDetails;
        }


        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(token);
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setData(new JwtResponse(token));
        return apiResponse;
    }

    @Override
    public void authenticate(String username, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }

    }

    @Override
    public long getTimeDifferenceFromCurrentTime(Timestamp timestamp) {
        Date date = new Date();
        Timestamp currentTimeStamp = new Timestamp(date.getTime());
        ;
        long milliseconds = currentTimeStamp.getTime() - timestamp.getTime();
        System.out.println(milliseconds);
        return milliseconds / 1000;
    }

    @Override
    public ApiResponse<String> generateAndSetOtp(OtpField otpField) {
        Users users = this.usersService.findUsersByMobileNumber(otpField.getMobileNumber());
        users.setOtp("000000");
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        users.setOtpGenerationTime(ts);
        this.usersService.updateUsers(users,users.getUserId());
        ApiResponse<String > apiResponse = new ApiResponse<>();
        apiResponse.setData("Otp Generated successfully");
        apiResponse.setHttpStatus(HttpStatus.CREATED);
        return apiResponse;
    }
}
