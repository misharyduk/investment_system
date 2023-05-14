package com.ryduk.investment_system.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Investment {
    @SequenceGenerator(
            name = "investment_sequence_generator",
            sequenceName = "investment_sequence_generator"
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "investment_sequence_generator"
    )
    private int id;
    @NotBlank
    private double investmentAmount;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date term;
    @OneToOne
    private InvestmentType investment;

}
