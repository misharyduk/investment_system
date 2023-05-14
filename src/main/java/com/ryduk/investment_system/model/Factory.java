package com.ryduk.investment_system.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Factory {
    @SequenceGenerator(
            name = "factory_sequence_generator",
            sequenceName = "factory_sequence_generator"
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "factory_sequence_generator"
    )
    private int id;
    @Length(min = 5, max = 255)
    @NotBlank
    private String name;
    @Length(min = 5, max = 255)
    @NotBlank
    private String typeOfProperty;
    @Length(min = 5, max = 255)
    @NotBlank
    private String address;
    @Pattern(regexp = "\\d{10,12}")
    @NotBlank
    private long telephone;
}
