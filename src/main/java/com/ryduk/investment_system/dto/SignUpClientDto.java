package com.ryduk.investment_system.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignUpClientDto {
    @Length(min = 5, max = 255)
    private String username;
    @Length(min = 4, max = 255)
    private String password;
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
