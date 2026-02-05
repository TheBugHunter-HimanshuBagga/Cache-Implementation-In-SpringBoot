package com.HimanshuBagga.CachingImplementation.services;

import com.HimanshuBagga.CachingImplementation.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    //getEmployeeById
    EmployeeDto getEmployeebyId(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployeeById(Long employeeId , EmployeeDto employeeDto);
}
