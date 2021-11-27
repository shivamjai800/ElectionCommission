package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.entity.Part;
import com.electioncomission.ec.repository.PartRepository;
import com.electioncomission.ec.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    PartRepository partRepository;

    @Override
    public Part addPart(Part part) {
        return this.partRepository.save(part);
    }

    @Override
    public Part updatePart(Part part, int partId) {
        if (this.partRepository.findPartByPartId(partId) != null)
            return this.partRepository.save(part);
        return null;
    }

    @Override
    public Part findPartByPartId(int partId) {
        return this.partRepository.findPartByPartId(partId);
    }

    @Override
    public void deletePartByPartId(int partId) {
        this.partRepository.deletePartByPartId(partId);
    }
}
