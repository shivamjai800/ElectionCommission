package com.electioncomission.ec.service.implementation;

import com.electioncomission.ec.entity.Constituency;
import com.electioncomission.ec.repository.ConstituencyRepository;
import com.electioncomission.ec.service.ConstituencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstituencyServiceImpl implements ConstituencyService {

    @Autowired
    ConstituencyRepository constituencyRepository;

    @Override
    public Constituency addConstituency(Constituency constituency) {
        return this.constituencyRepository.save(constituency);
    }

    @Override
    public Constituency updateConstituency(Constituency constituency, int constituencyId) {
        if (this.constituencyRepository.findConstituencyByConstituencyId(constituencyId) != null)
            return this.constituencyRepository.save(constituency);
        return null;
    }

    @Override
    public Constituency findConstituencyByConstituencyId(int constituencyId) {
        return this.constituencyRepository.findConstituencyByConstituencyId(constituencyId);
    }

    @Override
    public void deleteConstituencyByConstituencyId(int constituencyId) {
        this.constituencyRepository.deleteConstituencyByConstituencyId(constituencyId);
    }
}
