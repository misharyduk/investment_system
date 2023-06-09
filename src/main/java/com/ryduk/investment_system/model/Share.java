package com.ryduk.investment_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Share extends InvestmentType{
    @Size(min = 0, max = 4000)
    @NotBlank
    private double price;
    @ManyToMany
    private List<Factory> ownershipHistory = new ArrayList<>();
    private boolean isTaken;
}
