package com.ryduk.investment_system.service;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.EntityPageDto;
import com.ryduk.investment_system.model.Factory;
import com.ryduk.investment_system.repo.FactoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactoryService {
    private final FactoryRepo factoryRepo;


    public Factory saveFactory(Factory factory){
        return factoryRepo.save(factory);
    }

    public Factory getFactory(int factoryId){
        return factoryRepo.findById(factoryId)
                .orElse(null);
    }

    public List<Factory> getAllFactories(){
        return factoryRepo.findAll();
    }

    public boolean deleteFactory(int factoryId){
        Factory factoryInDb = getFactory(factoryId);
        if(factoryInDb == null)
            return false;

        factoryRepo.deleteById(factoryId);
        return true;
    }
}
