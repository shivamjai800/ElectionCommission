package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.entity.District;
import com.electioncomission.ec.repository.DistrictRepository;
import com.electioncomission.ec.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public District findDistrictByDistrictId(int districId) {
        return this.districtRepository.findDistrictByDistrictId(districId);

    }

    @Override
    public void deleteDistrictByDistrictId(int districId) {
        this.districtRepository.deleteDistrictByDistrictId(districId);
    }

    @Override
    public District addDistrict(District district) {
        return this.districtRepository.save(district);
    }

    @Override
    public District updateDistrict(District district, int districtId) {
         return this.districtRepository.save(district);
    }
}
