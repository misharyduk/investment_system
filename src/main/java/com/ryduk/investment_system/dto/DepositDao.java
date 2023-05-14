package com.ryduk.investment_system.dto;

import com.ryduk.investment_system.model.Deposit;
import com.ryduk.investment_system.model.InvestmentType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DepositDao {
    private int investmentId;
    private String investmentType;
    private double amount;
    private double profit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date term;
    private Deposit pureDeposit;
}
