package com.electioncomission.ec.service;

import com.electioncomission.ec.common.ApiResponse;
import com.electioncomission.ec.entity.District;
import com.electioncomission.ec.model.AreaUpdate;

import java.security.Principal;
import java.util.List;

public interface DistrictService {

    public District addDistrict(District district);
    public ApiResponse<String> updateDistrict(AreaUpdate areaUpdate, int districtId, Principal principal);
    public District findDistrictByDistrictId(int districtId);
    public void deleteDistrictByDistrictId(int districId);
    public List<District> findAllDistricts();


}
