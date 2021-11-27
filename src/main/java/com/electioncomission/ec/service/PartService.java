package com.electioncomission.ec.service;

import com.electioncomission.ec.entity.Part;

public interface PartService {

    public Part addPart(Part part);
    public Part updatePart(Part part, int partId);
    public Part findPartByPartId(int partId);
    public void deletePartByPartId(int partId);
}
