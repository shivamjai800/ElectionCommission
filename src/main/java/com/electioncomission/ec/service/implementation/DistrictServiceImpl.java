package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiErrorCode;
import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.common.SuccessMessage;
import com.electioncomission.ec.entity.District;
import com.electioncomission.ec.entity.Users;
import com.electioncomission.ec.model.AreaUpdate;
import com.electioncomission.ec.model.Enums;
import com.electioncomission.ec.model.OfficerRoles;
import com.electioncomission.ec.repository.DistrictRepository;
import com.electioncomission.ec.service.DistrictService;
import com.electioncomission.ec.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    UsersService usersService;

    @Override
    public District findDistrictByDistrictId(int districId) {
        return this.districtRepository.findDistrictByDistrictId(districId);

    }

    @Override
    public void deleteDistrictByDistrictId(int districId) {
        this.districtRepository.deleteDistrictByDistrictId(districId);
    }

    @Override
    public List<District> findAllDistricts() {
        return (List)this.districtRepository.findAll();
    }

    @Override
    public District addDistrict(District district) {
        return this.districtRepository.save(district);
    }

    @Override
    public ApiResponse<String> updateDistrict(AreaUpdate areaUpdate, int districtId, Principal principal) {
        District district = this.findDistrictByDistrictId(districtId);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if(district==null)
        {
            apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            apiResponse.setApiError(new ApiError(ApiErrorCode.AREA_NOT_FOUND));
        }
        else
        {
            Users users = this.usersService.findUsersByUserId(Integer.parseInt(principal.getName()));
            if(!users.getUserRole().equals(Enums.UsersRole.DEO.getValue()))
            {
                apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
                apiResponse.setApiError(new ApiError(ApiErrorCode.CANNOT_PERFORM_ACTION));
                apiResponse.getApiError().setSubMessage("You are not DEO");
            }
            else
            {
                if(users.getDistrictId()!=districtId)
                {
                    apiResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
                    apiResponse.setApiError(new ApiError(ApiErrorCode.CANNOT_PERFORM_ACTION));
                    apiResponse.getApiError().setSubMessage("District outside your area");
                }
                else
                {
                    if(district.getLock()!=null &&district.getLock().equals(areaUpdate.getLock()))
                    {
                        apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
                        apiResponse.setApiError(new ApiError(areaUpdate.getLock()?ApiErrorCode.AREA_ALREADY_LOCKED:ApiErrorCode.AREA_ALREADY_UNLOCKED));
                    }
                    else {
                        district.setLock(areaUpdate.getLock());
                        this.districtRepository.save(district);
                        apiResponse.setHttpStatus(HttpStatus.OK);

                        apiResponse.setData(areaUpdate.getLock()?SuccessMessage.AREA_LOCKED_SUCCESSFULLY.getMessage():SuccessMessage.AREA_UNLOCKED_SUCCESSFULLY.getMessage());
                    }
                }
            }

        }
        return apiResponse;
    }
}
