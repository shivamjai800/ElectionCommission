package com.electioncomission.ec.service;

import com.electioncomission.ec.entity.Part;

import java.util.List;

public interface PartService {

    public Part addPart(Part part);
    public Part updatePart(Part part, int partId);
    public Part findPartByPartId(int partId);
    public void deletePartByPartId(int partId);

    public List<String> findAllPartNameByConstituencyId(int constituencyId);
    public List<String> findAllPartNameByConstituencyName(String constituencyName);
}
