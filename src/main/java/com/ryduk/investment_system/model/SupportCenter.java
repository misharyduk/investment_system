package com.ryduk.investment_system.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SupportCenter {
    @OneToMany
    private List<Client> clients;
    private List<PeaceOfAdvice> advices = new ArrayList<>();

    public SupportCenter(){
        try {
            advices = readAdvicesFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file");
        }
    }

    private List<PeaceOfAdvice> readAdvicesFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new FileInputStream("data/advices.json"), new TypeReference<List<PeaceOfAdvice>>(){});

    }

    @Data
    public static class PeaceOfAdvice{
        private int number;
        private String advice;

    }
}
