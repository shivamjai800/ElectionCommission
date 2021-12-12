package com.electioncomission.ec.service;

import com.electioncomission.ec.entity.District;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DistrictService {

    public District addDistrict(District district);
    public District updateDistrict(District district,int districtId);
    public District findDistrictByDistrictId(int districtId);
    public void deleteDistrictByDistrictId(int districId);
    public List<District> findAllDistricts();
}
