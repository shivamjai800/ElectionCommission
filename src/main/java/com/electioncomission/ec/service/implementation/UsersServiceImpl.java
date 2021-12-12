package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiErrorCode;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.model.Enums;
import com.electioncomission.ec.repository.UsersRepository;
import com.electioncomission.ec.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

import static com.electioncomission.ec.specifications.UserSpecifications.getUsersByUserRoleAndKeyword;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users addUsers(Users users) {
        return this.usersRepository.save(users);
    }

    @Override
    public Users updateUsers(Users users, int userId) {
        if (this.usersRepository.findUsersByUserId(userId) != null)
            return this.usersRepository.save(users);
        return null;
    }

    @Override
    public Users findUsersByUserId(int userId) {
        return this.usersRepository.findUsersByUserId(userId);
    }

    @Override
    public void deleteUsersByUserId(int userId) {
        this.usersRepository.deleteUsersByUserId(userId);
    }

    @Override
    public Users findUsersByMobileNumber(String mobileNumber) {
        return this.usersRepository.findUsersByMobileNumber(mobileNumber);
    }

    @Override
    public Users findUsersByUserName(String username) {
        return this.usersRepository.findUsersByUserName(username);
    }

    public ApiResponse<List<Users>> findBloByConstituencyIdAndKeyword(Principal principal, String keyword)
    {
        ApiResponse<List<Users>> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else
        {
            Users users = this.findUsersByUserId(Integer.parseInt(principal.getName()));
            if(!users.getUserRole().equals(Enums.UsersRole.RO.getValue()))
            {
                apiResponse.setHttpStatus(HttpStatus.FORBIDDEN);
                apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_PERMITTED));
            }
            else
            {
                apiResponse.setHttpStatus(HttpStatus.OK);
                List<Users> bloList = this.usersRepository.findAll(getUsersByUserRoleAndKeyword(users.getConstituencyId(), keyword, Enums.UsersRole.BLO.getValue()));
                apiResponse.setData(bloList);
            }
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<String> updateBloMobileNumber(Principal principal, Integer bloId, String mobileNumber) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if(principal==null)
        {
            apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
            apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_LOGGED_IN));
        }
        else {
            Users users = this.findUsersByUserId(Integer.parseInt(principal.getName()));
            if (!users.getUserRole().equals(Enums.UsersRole.RO.getValue())) {
                apiResponse.setHttpStatus(HttpStatus.FORBIDDEN);
                apiResponse.setApiError(new ApiError(ApiErrorCode.USER_NOT_PERMITTED));
            }
            else if(this.findUsersByMobileNumber(mobileNumber)!=null)
            {
                apiResponse.setHttpStatus(HttpStatus.EXPECTATION_FAILED);
                apiResponse.setApiError(new ApiError(ApiErrorCode.MOBILE_NUMBER_ALREADY_EXISTS));
            }
            else {
                users = this.findUsersByUserId(bloId);
                users.setMobileNumber(mobileNumber);
                this.updateUsers(users,bloId);
                apiResponse.setHttpStatus(HttpStatus.OK);
                apiResponse.setData("Mobile Number saved successfully");
            }
        }
        return apiResponse;
    }

}
