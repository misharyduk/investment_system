package com.ryduk.investment_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {
    @SequenceGenerator(
            name = "client_sequence_generator",
            sequenceName = "client_sequence_generator"
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence_generator"
    )
    private int id;
    @OneToOne
    private Factory factory;
    @OneToMany
    private List<Investment> investments;

    // USER credentials
    private String username;
    private String password;
    private String role;

}
