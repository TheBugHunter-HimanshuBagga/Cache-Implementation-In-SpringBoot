package com.HimanshuBagga.CachingImplementation.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String role;

    private Double Salary;

    private LocalDate dateOfJoining;

    private Boolean isActive;

}

