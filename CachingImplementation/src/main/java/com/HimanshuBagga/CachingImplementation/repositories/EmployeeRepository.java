package com.HimanshuBagga.CachingImplementation.repositories;

import com.HimanshuBagga.CachingImplementation.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee , Long> {
}
