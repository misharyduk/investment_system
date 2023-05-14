package com.ryduk.investment_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Deposit extends InvestmentType{
    @Length(min = 5, max = 255)
    @NotBlank
    private String bankName;
    @Size(min = 0, max = 100)
    @NotBlank
    private double interestRate;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date term;
    private boolean isTaken;
}
