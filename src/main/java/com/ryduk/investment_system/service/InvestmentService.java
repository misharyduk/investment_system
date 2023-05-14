package com.ryduk.investment_system.service;

import com.ryduk.investment_system.PaginationParameters;
import com.ryduk.investment_system.dto.EntityPageDto;
import com.ryduk.investment_system.model.Deposit;
import com.ryduk.investment_system.model.Investment;
import com.ryduk.investment_system.model.Share;
import com.ryduk.investment_system.repo.InvestmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentService {
    private final InvestmentRepo investmentRepo;

    private Investment saveInvestment(Investment investment){
        return investmentRepo.save(investment);
    }

    public Investment getInvestment(int investmentId){
        return investmentRepo.findById(investmentId)
                .orElse(null);
    }

    public EntityPageDto<Investment> getAllInvestments(PaginationParameters params){
        Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

        Page<Investment> page = investmentRepo.findAll(
                PageRequest.of(params.getPage() - 1, params.getPageSize())
                        .withSort(Sort.by(direction, params.getField())));
        List<Investment> investments = page.stream().toList();

        return new EntityPageDto<>(
                page.getTotalElements(), page.getTotalPages(), params.getPage(), params.getPageSize(), investments);
    }

    public boolean deleteInvestment(int investmentId){
        Investment investmentInDb = getInvestment(investmentId);
        if(investmentInDb == null)
            return false;

        investmentRepo.deleteById(investmentId);
        return true;
    }

    public Investment makeInvestment(Investment investment) {
        return saveInvestment(investment);
    }

    public double calculateProfit(Investment investment){
        int monthsOfInvestment = 0;
        if(investment.getInvestment() instanceof Deposit deposit){
            monthsOfInvestment = (int)
                    ((deposit.getTerm().getTime() - investment.getTerm().getTime()) / 1000 / 60 / 60 / 24 / 30);
        } else{
            Share share = (Share) investment.getInvestment();
        }
        monthsOfInvestment = 12;
        double profit = investment.getInvestmentAmount() * monthsOfInvestment;
        if(investment.getInvestment() instanceof Deposit deposit){
            profit = profit * (deposit.getInterestRate() / 100);
        }
        return profit;
    }

    public EntityPageDto<Investment> getAllInvestmentsByUser(PaginationParameters params, int userId) {
        Page<Investment> page = investmentRepo.findAll(/*userId, */PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(Sort.by(params.getDirection(), params.getField())));
        List<Investment> investments = page.stream().toList();

        return new EntityPageDto<>(
                page.getTotalElements(), page.getTotalPages(), params.getPage(), params.getPageSize(), investments);
    }
}
