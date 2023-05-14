package com.ryduk.investment_system.service;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.EntityPageDto;
import com.ryduk.investment_system.model.Client;
import com.ryduk.investment_system.model.Deposit;
import com.ryduk.investment_system.model.Investment;
import com.ryduk.investment_system.repo.DepositRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepo depositRepo;

    public Deposit saveDeposit(Deposit deposit){
        return depositRepo.save(deposit);
    }

    public Deposit getDeposit(int depositId){
        return depositRepo.findById(depositId)
                .orElse(null);
    }

    public EntityPageDto<Deposit> getAllDeposits(PaginationParameters params){
        Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

        Page<Deposit> page = depositRepo.findByIsTakenFalse(PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(Sort.by(direction, params.getField())));
        List<Deposit> deposits = page.stream().toList();

        return new EntityPageDto<>(
                page.getTotalElements(), page.getTotalPages(), params.getPage(), params.getPageSize(), deposits);
    }

    public long getCount(){
        return depositRepo.count();
    }
    public long getCountOfAvailable(){
        return depositRepo.countByIsTakenFalse();
    }

    public boolean deleteDeposit(int depositId){
        Deposit depositInDb = getDeposit(depositId);
        if(depositInDb == null)
            return false;

        depositRepo.deleteById(depositId);
        return true;
    }

    public boolean confirmInvestment(Investment investment){
        Deposit deposit = (Deposit) investment.getInvestment();
        if(investment.getTerm().getTime() < deposit.getTerm().getTime())
            return false;

        depositRepo.confirmInvestment(deposit.getId());

        return true;
    }

    public void releaseInvestment(int depositId){
        depositRepo.releaseInvestment(depositId);
    }
}
