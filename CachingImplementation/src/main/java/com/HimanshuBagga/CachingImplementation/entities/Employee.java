package com.HimanshuBagga.CachingImplementation.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Target;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private LocalDate dateOfJoining;

    private Boolean isActive;

    private String role;

    private Double salary;
}
