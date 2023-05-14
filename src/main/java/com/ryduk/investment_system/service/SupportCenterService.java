package com.ryduk.investment_system.service;

import com.ryduk.investment_system.model.Client;
import com.ryduk.investment_system.model.SupportCenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupportCenterService {
    private static final SupportCenter supportCenter = new SupportCenter();

    public String clientAnalysis(Client client){

        // Get number of all investments
        int numberOfInvestments = client.getInvestments().size() == 0 ? 0 : client.getInvestments().size() + 5;
        // Map advices from file to numbers
        List<Integer> numbersFromJsonFile = supportCenter.getAdvices().stream()
                .map(SupportCenter.PeaceOfAdvice::getNumber).collect(Collectors.toList());
        Collections.reverse(numbersFromJsonFile);
        // Find most accurate number form this array
        int mostAccurateNumber = numbersFromJsonFile.stream()
                .filter((number) -> number <= numberOfInvestments).findFirst().orElse(0);
        // Get advice by its number
        SupportCenter.PeaceOfAdvice peaceOfAdvice = supportCenter.getAdvices().stream()
                .filter((advice) -> advice.getNumber() == mostAccurateNumber).findFirst().orElseThrow(
                        () -> {throw new IllegalArgumentException("No advice found");});

        return peaceOfAdvice.getAdvice();
    }
}
