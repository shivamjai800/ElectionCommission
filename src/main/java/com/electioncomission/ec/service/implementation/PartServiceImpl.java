package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.entity.Part;
import com.electioncomission.ec.repository.PartRepository;
import com.electioncomission.ec.service.ConstituencyService;
import com.electioncomission.ec.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    PartRepository partRepository;

    @Autowired
    ConstituencyService constituencyService;

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

    @Override
    public List<String> findAllPartNameByConstituencyId(int constituencyId) {
//        return this.partRepository.findAllPartNameByConstituencyId(constituencyId);
        List<Part> listPart = this.partRepository.findAllPartNameByConstituencyId(constituencyId);
        List<String> partNames = new ArrayList<>();
        listPart.forEach(e->{
            partNames.add(e.getPartName());
        });
        return partNames;
    }

    @Override
    public List<String> findAllPartNameByConstituencyName(String constituencyName) {
        int constituencyId = this.constituencyService.findConstituencyByConstituencyName(constituencyName).getConstituencyId();
        List<Part> listPart = this.partRepository.findAllPartNameByConstituencyId(constituencyId);
        List<String> partNames = new ArrayList<>();
        listPart.forEach(e->{
            partNames.add(e.getPartName());
        });
        return partNames;
    }

    @Override
    public List<Part> findPartsByConstituencyId(int constituencyId) {
        List<Part> listPart = this.partRepository.findAllPartNameByConstituencyId(constituencyId);
        return listPart;
    }
}
