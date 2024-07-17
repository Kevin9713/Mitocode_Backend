package com.mitocode.service.impl;

import com.mitocode.model.VitalSigns;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IVitalSignsRepo;
import com.mitocode.service.IVitalSignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VitalSignServiceImpl extends CRUDImpl<VitalSigns, Integer> implements IVitalSignService {

    private final IVitalSignsRepo repo;

    @Override
    protected IGenericRepo<VitalSigns, Integer> getRepo() {
        return repo;
    }
}
