package com.ryduk.investment_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InvestmentType {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    @Length(min = 5, max = 255)
    @NotBlank
    protected String name;
}
