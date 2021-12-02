package com.electioncomission.ec.service;

import com.electioncomission.ec.entity.Constituency;

import java.util.List;

public interface ConstituencyService {
    public Constituency addConstituency(Constituency constituency);
    public Constituency updateConstituency(Constituency constituency, int constituencyId);
    public Constituency findConstituencyByConstituencyId(int constituencyId);
    public void deleteConstituencyByConstituencyId(int constituencyId);

    public List<String> findAllConstituencyNameByDistrictId(int districtId);

    public Constituency findConstituencyByConstituencyName(String constituencyName);
}
