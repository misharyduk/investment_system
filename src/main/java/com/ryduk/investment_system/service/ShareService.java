package com.ryduk.investment_system.service;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.EntityPageDto;

import com.ryduk.investment_system.model.Deposit;
import com.ryduk.investment_system.model.Investment;
import com.ryduk.investment_system.model.Share;
import com.ryduk.investment_system.repo.ShareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ShareService {
    private final ShareRepo shareRepo;

    public Share saveShare(Share share){
        return shareRepo.save(share);
    }

    public Share getShare(int shareId){
        return shareRepo.findById(shareId)
                .orElse(null);
    }

    public EntityPageDto<Share> getAllShares(PaginationParameters params){
        Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

        Page<Share> page = shareRepo.findByIsTakenFalse(PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(Sort.by(direction, params.getField())));
        List<Share> shares = page.stream().toList();

        return new EntityPageDto<>(
                page.getTotalElements(), page.getTotalPages(), params.getPage(), params.getPageSize(), shares);
    }

    public long getCount(){
        return shareRepo.count();
    }

    public long getCountOfAvailable(){
        return shareRepo.countByIsTakenFalse();
    }

    public boolean deleteShare(int shareId){
        Share shareInDb = getShare(shareId);
        if(shareInDb == null)
            return false;

        shareRepo.deleteById(shareId);
        return true;
    }

    public boolean confirmInvestment(Investment investment){
        Share share = (Share) investment.getInvestment();
        // TODO: Check for date
        if(investment.getInvestmentAmount() < share.getPrice())
            return false;
        shareRepo.confirmInvestment(share.getId());

        return true;
    }

    public void releaseInvestment(int investmentId) {
        shareRepo.releaseInvestment(investmentId);
    }
}
