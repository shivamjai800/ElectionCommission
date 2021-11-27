package com.electioncomission.ec.controller;

import com.electioncomission.ec.entity.District;
import com.electioncomission.ec.service.DistrictService;
import com.electioncomission.ec.service.implementation.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class TestController {

    @Autowired
    DistrictService districtService;

    @PostMapping("/test/district")
    public District addDistrict(HttpServletRequest request, @RequestBody District district)
    {
        return this.districtService.addDistrict(district);
    }
    @GetMapping("/test/district/{districtId}")
    public District getDistrict(HttpServletRequest request, @PathVariable int districtId)
    {
        return this.districtService.findDistrictByDistrictId(districtId);
    }
    @PutMapping("/test/district/{districtId}")
    public District updateDistrict(HttpServletRequest request, @RequestBody District district,@PathVariable("districtId") int districtId)
    {
        return this.districtService.updateDistrict(district, districtId);
    }
    @DeleteMapping("/test/district/{districtId}")
    public void deleteDistrict(HttpServletRequest request, @PathVariable("districtId") int districtId)
    {
        this.districtService.deleteDistrictByDistrictId(districtId);
    }
}
